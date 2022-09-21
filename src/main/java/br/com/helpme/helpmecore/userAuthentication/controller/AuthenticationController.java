package br.com.helpme.helpmecore.userAuthentication.controller;

import br.com.helpme.helpmecore.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Scope("prototype")
public class AuthenticationController {

    private static final String IS_AUTHENTICATED = "isAuthenticated";
    private boolean isAuthenticated = false;
    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String showLogin(Model model)
    {
        model.addAttribute(IS_AUTHENTICATED, isAuthenticated);
        return "login";
    }

}
