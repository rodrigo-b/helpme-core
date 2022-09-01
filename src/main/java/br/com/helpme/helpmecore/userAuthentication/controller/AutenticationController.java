package br.com.helpme.helpmecore.userAuthentication.controller;

import br.com.helpme.helpmecore.user.model.User;
import br.com.helpme.helpmecore.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@Scope("prototype")
public class AutenticationController {

    private static final String IS_AUTHENTICATED = "isAuthenticated";
    private boolean isAuthenticated = false;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public RedirectView doLogin(User user, Model model){

        Optional<User> userOptional = userService.findUserByEmailAndPassword(user);

        if(userOptional.isPresent()) {
            isAuthenticated = true;
            model.addAttribute(IS_AUTHENTICATED, isAuthenticated);
            return new RedirectView("/");
        }
        else
            return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String showLogin(Model model)
    {
        model.addAttribute(IS_AUTHENTICATED, isAuthenticated);
        return "login";
    }

}
