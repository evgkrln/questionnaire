package com.evgkrln.questionnaire.controller;

import javax.validation.Valid;

import com.evgkrln.questionnaire.model.User;
import com.evgkrln.questionnaire.service.UserService;
import com.evgkrln.questionnaire.util.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/responses";
        } else {
            User user = new User();
            model.addAttribute("user", user);
            return "registration";
        }
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        } else if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("confirm_error", "Passwords don't match");
            return "registration";
        } else if (!createUserAccount(user, false)) {
            model.addAttribute("email_exists", "An account with this email address already exists");
            return "registration";
        } else {
            return "redirect:/login?info=need_confirm";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (!isActivated) {
            return "redirect:/login?info=not_confirm";
        } else {
            return "redirect:/login?info=good_confirm";
        }
    }

    private boolean createUserAccount(User user, boolean admin) {
        try {
            userService.registerNewUserAccount(user, admin);
            return true;
        } catch (EmailExistsException e) {
            return false;
        }
    }
}
