package br.com.helpme.helpmecore.user.controller;

import br.com.helpme.helpmecore.user.dto.PasswordDTO;
import br.com.helpme.helpmecore.user.dto.ProfileDTO;
import br.com.helpme.helpmecore.user.model.User;
import br.com.helpme.helpmecore.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Scope("prototype")
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String showProfilePage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> userOptional = userService.findByEmail(email);

        model.addAttribute("user",userOptional.get());
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String showProfilePageToEdit(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<User> userOptional = userService.findByEmail(email);

        model.addAttribute("user", userOptional.get());

        return "profile-edit";
    }

    @GetMapping("/password")
    public String showChangePassword(){
        return "password-edit";
    }

    @PostMapping("/password")
    public String changePassword(PasswordDTO passwordDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        userService.changePassword(email, passwordDTO);
        return "redirect:index";
    }

    @PostMapping("/profile/edit")
    public String changeProfile(ProfileDTO profileDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        userService.updateUserProfile(email,profileDTO);
        return "redirect:/users/profile";
    }
}
