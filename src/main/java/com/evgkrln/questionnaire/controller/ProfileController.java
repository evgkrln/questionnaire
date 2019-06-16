package com.evgkrln.questionnaire.controller;

import java.security.Principal;
import javax.validation.Valid;

import com.evgkrln.questionnaire.model.User;
import com.evgkrln.questionnaire.repository.UserRepository;
import com.evgkrln.questionnaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    private UserService userService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/editprofile")
    public String showEditProfileForm(Model model, Principal principal) {
        User authorizedUser = userRepository.findByEmail(principal.getName());
        model.addAttribute("name", authorizedUser.getEmail());
        model.addAttribute("user", authorizedUser);
        return "editprofile";
    }

    @PostMapping("/editprofile")
    public String EditProfile(@ModelAttribute("user") @Valid User user, Principal principal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            User edituser = userRepository.findByEmail(principal.getName());
            userService.EditUserProfile(user, edituser);
            return "redirect:/field";
        }
    }

    @GetMapping("/changepass")
    public String showChangePassForm(Model model, Principal principal) {
        User authorizedUser = userRepository.findByEmail(principal.getName());
        model.addAttribute("name", authorizedUser.getEmail());
        return "changepass";
    }

    @PostMapping("/changepass")
    public String ChangePass(Principal principal, Model model, @RequestParam(name = "curpass") String curpass, @RequestParam(name = "newpass") String newpass, @RequestParam(name = "confirmpass") String cofirmpass) {
        User edituser = userRepository.findByEmail(principal.getName());
        if (!passwordEncoder.matches(curpass, edituser.getPassword())) {
            model.addAttribute("error_curpass", "Wrong password");
            return "changepass";
        } else if (!newpass.equals(cofirmpass)) {
            model.addAttribute("error_confirm", "Passwords don't match");
            return "changepass";
        } else {
            userService.ChangeUserPass(edituser, newpass);
            return "redirect:/logout";
        }
    }
}
