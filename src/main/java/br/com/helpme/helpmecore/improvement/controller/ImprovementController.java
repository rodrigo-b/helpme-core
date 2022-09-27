package br.com.helpme.helpmecore.improvement.controller;

import br.com.helpme.helpmecore.improvement.dto.ImprovementDto;
import br.com.helpme.helpmecore.improvement.model.Classification;
import br.com.helpme.helpmecore.improvement.model.Improvement;
import br.com.helpme.helpmecore.improvement.service.ImprovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping(value = "/improvements")
public class ImprovementController {

    @Autowired
    private ImprovementService improvementService;
    @GetMapping
    public String showImprovementForm(Model model){
        model.addAttribute("classifications", Classification.values());
        return "newImprovement.html";

    }

    @PostMapping
    public String addNewImprovement(ImprovementDto improvementDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        improvementService.save(improvementDto.toImprovement(), email);

        return "index";
    }

    @GetMapping("/all")
    public String findAll(Model model, Pageable pageable){

        Page<Improvement> improvements = improvementService.findAll(pageable);
        model.addAttribute("improvements", improvements);

        return  "allImprovements";
    }


}


