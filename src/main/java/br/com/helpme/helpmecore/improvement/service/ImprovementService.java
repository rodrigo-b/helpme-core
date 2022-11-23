package br.com.helpme.helpmecore.improvement.service;

import br.com.helpme.helpmecore.improvement.dto.ImprovementDto;
import br.com.helpme.helpmecore.improvement.model.ClassificationPorcent;
import br.com.helpme.helpmecore.improvement.model.Improvement;
import br.com.helpme.helpmecore.improvement.model.UserImprovement;
import br.com.helpme.helpmecore.improvement.repository.ImprovementRepository;
import br.com.helpme.helpmecore.improvement.repository.UserImprovementRepository;
import br.com.helpme.helpmecore.user.model.User;
import br.com.helpme.helpmecore.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class ImprovementService {

    @Autowired
    private UserService userService;
    @Autowired
    private ImprovementRepository improvementRepository;

    @Autowired
    private UserImprovementRepository userImprovementRepository;
    @Autowired
    private SimilarityService similarityService;

    public Improvement save(Improvement improvement, String email){
        Optional<User> userOptional = userService.findByEmail(email);
        if(userOptional.isPresent()){
            improvement.setUser(userOptional.get());
            return improvementRepository.save(improvement);
        }else {
            throw new RuntimeException("Invalid user");
        }

    }

    public List<Improvement> findTopFiveImprovementsByLike(){
        return improvementRepository.findTop5ByOrderByLikesDesc();
    }

    public List<ClassificationPorcent> findClassificationsPorcent(){

        List<ClassificationPorcent> classificationsGrouped = improvementRepository.findAll()
                .parallelStream()
                .map(improvement -> new ClassificationPorcent(improvement.getClassification().name(), improvement.getLikes()))
                .collect(
                        Collectors.toMap(
                                ClassificationPorcent::getClassification,
                                Function.identity(),
                                (classification, classification2) -> {

                                    double total = classification.getTotalLikesInPorcent() + classification2.getTotalLikesInPorcent();
                                    return new ClassificationPorcent(classification.getClassification(), total);
                                }
                        )
                )
                .values()
                .stream()
                .sorted(Comparator.comparing(ClassificationPorcent::getClassification))
                .collect(Collectors.toList());


        final double totalLikesCounted = (classificationsGrouped.stream()
                                                        .mapToDouble(ClassificationPorcent::getTotalLikesInPorcent))
                                                        .sum();

        classificationsGrouped
                .forEach(classificationPorcent -> {
                    final double porcent = (classificationPorcent.getTotalLikesInPorcent() * 100) / totalLikesCounted;
                    double roundedPorcent = Math.round(porcent * 100.0) / 100.0;
                    classificationPorcent.setTotalLikesInPorcent(roundedPorcent);
                });

        return classificationsGrouped;

    }


    public Page<ImprovementDto> findAll(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> userOptional = userService.findByEmail(email);

        User user = userOptional.get();

        Page<Improvement> allPages = improvementRepository.findAll(pageable);

        List<ImprovementDto> improvementDtos = new ArrayList<>();

        allPages.forEach(improvement -> {
            ImprovementDto improvementDto = new ImprovementDto(improvement, user);
            Optional<UserImprovement> byIdUserAndIdImprovement = userImprovementRepository.findByIdUserAndIdImprovement(user.getIdUser(), improvement.getId());
            if(byIdUserAndIdImprovement.isPresent()){
                improvementDto.setLikedByUser(byIdUserAndIdImprovement.get().getLiked());
            }else{
                improvementDto.setLikedByUser(0);
            }

            improvementDtos.add(improvementDto);
        });

        return new PageImpl<>(improvementDtos,allPages.getPageable(), allPages.getTotalElements());
    }

    public List<Improvement> findAllWithoutPagination(){
        return improvementRepository.findAll();
    }

    public Page<Improvement> findAllUserImprovements(Pageable pageable, String email) {

        Optional<User> userOptional = userService.findByEmail(email);

        if(userOptional.isPresent()) {
            Page<Improvement> improvements = improvementRepository.findAllByUserId(userOptional.get().getIdUser(),pageable);
            return improvements;
        }

        throw new RuntimeException("Invallid user");
    }

    public List<Improvement> findSimilarityImprovements(String improvementSearch) {

        return findAllWithoutPagination()
                .stream()
                .filter(improvement -> {
                    double similarity = similarityService.checkSimilarity(improvementSearch, improvement.getTitle());
                    return similarity >= 40.0;
                })
                .toList();

    }

    public int changeLikeState(String email, String title) {

        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        Optional<Improvement> improvementOptional = improvementRepository.findByTitle(title);

        if(improvementOptional.isEmpty()){
            throw new RuntimeException("Invalid improvement");
        }
        Improvement improvement = improvementOptional.get();

        Optional<UserImprovement> userImprovementOptional = userImprovementRepository.findByIdUserAndIdImprovement(user.getIdUser(), improvement.getId());

        if(userImprovementOptional.isPresent()){
            UserImprovement userImprovement = userImprovementOptional.get();
            userImprovement.changeState();
            userImprovementRepository.save(userImprovement);
            return userImprovement.getLiked();
        }else{
            UserImprovement userImprovement = new UserImprovement(user.getIdUser(), improvement.getId());
            return userImprovementRepository.save(userImprovement).getLiked();
        }
    }
}