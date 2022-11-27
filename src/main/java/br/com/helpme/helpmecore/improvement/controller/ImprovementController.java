package br.com.helpme.helpmecore.improvement.controller;

import br.com.helpme.helpmecore.improvement.dto.ImprovementDto;
import br.com.helpme.helpmecore.improvement.model.Classification;
import br.com.helpme.helpmecore.improvement.model.Improvement;
import br.com.helpme.helpmecore.improvement.service.ImprovementService;
import br.com.helpme.helpmecore.improvement.util.ImprovementPageManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping(value = "/improvements")
public class ImprovementController {

    @Autowired
    private ImprovementService improvementService;
    @GetMapping("/new")
    public String showImprovementForm(Model model){
        model.addAttribute("classifications", Classification.values());
        return "newImprovement.html";
    }

    @PostMapping("/change-like")
    public ResponseEntity<?> countImprovementLike(@RequestBody String title){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        int liked = improvementService.changeLikeState(email, title);

        return ResponseEntity.ok(liked);
    }

    @PostMapping
    public String addNewImprovement(ImprovementDto improvementDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        improvementService.save(improvementDto.toImprovement(), email);

        return "redirect:index";
    }

    @GetMapping("/all")
    public String findAll(Model model, Pageable pageable){

        ImprovementPageManagement improvementPageManagement;

        Page<ImprovementDto> improvements = improvementService.findAll(pageable);
        improvementPageManagement = new ImprovementPageManagement(improvements.getTotalPages());
        model.addAttribute("improvements", improvements);

        model.addAttribute("pages", improvementPageManagement.getPages());
        model.addAttribute("next", improvementPageManagement.getNextPage());
        model.addAttribute("previuos", improvementPageManagement.getPreviousPage());

        return  "allImprovements";
    }

    @GetMapping("/user")
    public String findAllUserImprovements(Model model, Pageable pageable) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Page<Improvement> improvements = improvementService.findAllUserImprovements(pageable, email);
        ImprovementPageManagement improvementPageManagement = new ImprovementPageManagement(improvements);

        model.addAttribute("improvements", improvements);
        model.addAttribute("pages", improvementPageManagement.getPages());
        model.addAttribute("next", improvementPageManagement.getNextPage());
        model.addAttribute("previuos", improvementPageManagement.getPreviousPage());

        return "userImprovement";
    }

    @GetMapping("/search")
    public String searchImprovement(@RequestParam  String search, Model model){

        List<Improvement> improvements = improvementService.findSimilarityImprovements(search.toUpperCase());

        model.addAttribute("improvements", improvements);
        return "searchResult";

    }

}


