package br.com.helpme.helpmecore;

import br.com.helpme.helpmecore.improvement.model.ClassificationPorcent;
import br.com.helpme.helpmecore.improvement.model.Improvement;
import br.com.helpme.helpmecore.improvement.service.ImprovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private static final String IMPROVEMENTS = "improvements";
    private static final String CLASSIFICATION_PORCENTS = "classificationsPorcents";

    @Autowired
    private ImprovementService improvementService;

    @GetMapping("/index")
    public String showIndex(Model model){

        List<Improvement> topFiveImprovementsByLike = improvementService.findTopFiveImprovementsByLike();
        List<ClassificationPorcent> classificationsPorcents = improvementService.findClassificationsPorcent();

        model.addAttribute(IMPROVEMENTS,topFiveImprovementsByLike);
        model.addAttribute(CLASSIFICATION_PORCENTS, classificationsPorcents);
        return "index";
    }

}
