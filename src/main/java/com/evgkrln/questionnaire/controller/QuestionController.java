package com.evgkrln.questionnaire.controller;

import java.security.Principal;

import com.evgkrln.questionnaire.model.Response;
import com.evgkrln.questionnaire.model.User;
import com.evgkrln.questionnaire.repository.FieldRepository;
import com.evgkrln.questionnaire.repository.ResponseRepository;
import com.evgkrln.questionnaire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionController {
    private FieldRepository fieldRepository;
    private UserRepository userRepository;
    private ResponseRepository responseRepository;

    @Autowired
    public QuestionController(FieldRepository fieldRepository, UserRepository userRepository, ResponseRepository responseRepository) {
        this.fieldRepository = fieldRepository;
        this.userRepository = userRepository;
        this.responseRepository = responseRepository;
    }

    @GetMapping({"/questions", "/"})
    public String showQuestions(Model model, Principal principal) {
        model.addAttribute("questions", fieldRepository.findAllByOrderByIdfAsc());
        return "questions";
    }

    @MessageMapping("/responses")
    @SendTo("/topic/responses")
    public Response sendResponse(@Payload Response response) throws InterruptedException {
        return responseRepository.save(response);
    }

    @GetMapping("/responses")
    public String showResponses(Model model, Principal principal) {
        User authorizedUser = userRepository.findByEmail(principal.getName());
        model.addAttribute("name", authorizedUser.getEmail());
        model.addAttribute("questions", fieldRepository.findAllByOrderByIdfAsc());
        model.addAttribute("responses", responseRepository.findAll());
        return "responses";
    }
}
