package br.com.helpme.helpmecore.improvement.controller;

import br.com.helpme.helpmecore.improvement.dto.ImprovementDto;
import br.com.helpme.helpmecore.improvement.model.Classification;
import br.com.helpme.helpmecore.improvement.model.Improvement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Scope("prototype")
public class ImprovementController {

    @GetMapping("/improvement")
    public String showImprovementForm(Model model){
        model.addAttribute("classifications", Classification.values());
        return "newImprovement.html";

    }

    @PostMapping("/improvement")
    public String addNewImprovement(ImprovementDto improvementDto){

        String classification = improvementDto.getClassification();
        String title = improvementDto.getTitle();
        String message = improvementDto.getMessage();


        return "index";
    }


}
