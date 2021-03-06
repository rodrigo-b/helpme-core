package br.com.helpme.helpmecore.user.controller;

import br.com.helpme.helpmecore.user.model.User;
import br.com.helpme.helpmecore.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@Scope("prototype")
public class AutenticationController {

    private static final String IS_AUTHENTICATED = "isAuthenticated";
    private boolean isAuthenticated = false;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String doLogin(User user, Model model){

        Optional<User> userOptional = userService.findUserByEmailAndPassword(user);

        if(userOptional.isPresent()) {
            isAuthenticated = true;
            model.addAttribute(IS_AUTHENTICATED, isAuthenticated);
            return "index";
        }
        else
            return null;
    }

    @GetMapping("/login")
    public String showLogin(Model model)
    {
        model.addAttribute(IS_AUTHENTICATED, isAuthenticated);
        return "login";
    }

}
