package br.com.helpme.helpmecore.userAuthentication.controller;

import br.com.helpme.helpmecore.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("prototype")
public class AuthenticationController {

    private static final String IS_SUCESSFUL_AUTH = "IS_SUCESSFUL_AUTH";
    private boolean isSucessfulAuth;
    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String showLogin(Model model , @RequestParam(defaultValue = "false") boolean error)
    {
        if(error){
            String errorMessage = "Email or passaword invalid, please try again";
            model.addAttribute("MSG_ERROR", errorMessage);
            isSucessfulAuth = false;
        }else{
            isSucessfulAuth = true;
        }

        model.addAttribute(IS_SUCESSFUL_AUTH, isSucessfulAuth);
        return "login";
    }

}
