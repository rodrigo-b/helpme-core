package br.com.helpme.helpmecore.improvement.service;

import br.com.helpme.helpmecore.improvement.model.ClassificationPorcent;
import br.com.helpme.helpmecore.improvement.model.Improvement;
import br.com.helpme.helpmecore.improvement.repository.ImprovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class ImprovementService {

    @Autowired
    private ImprovementRepository improvementRepository;

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


}