package com.evgkrln.questionnaire.controller;

import java.security.Principal;
import javax.validation.Valid;

import com.evgkrln.questionnaire.model.Field;
import com.evgkrln.questionnaire.model.User;
import com.evgkrln.questionnaire.repository.FieldRepository;
import com.evgkrln.questionnaire.repository.UserRepository;
import com.evgkrln.questionnaire.service.FieldService;
import com.evgkrln.questionnaire.util.exception.LabelExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FieldController {
    private FieldService fieldService;
    private FieldRepository fieldRepository;
    private UserRepository userRepository;

    @Autowired
    public FieldController(FieldService fieldService, FieldRepository fieldRepository, UserRepository userRepository) {
        this.fieldService = fieldService;
        this.fieldRepository = fieldRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/field")
    public String showField(Model model, Principal principal) {
        model.addAttribute("newfield", new Field());
        User authorizedUser = userRepository.findByEmail(principal.getName());
        model.addAttribute("name", authorizedUser.getEmail());
        model.addAttribute("data", fieldRepository.findAllByOrderByIdfAsc());
        return "field";
    }

    @PostMapping("/field")
    public String saveNewField(@ModelAttribute("newfield") @Valid Field field, @RequestParam(name = "active",required = false) String active, @RequestParam(name = "required",required = false) String required, Model model) {
        Field added = null;
        added = this.createField(field);
        if (added == null) {
            model.addAttribute("field_exists", "A field with this label already exists");
            return "field";
        } else {
            fieldService.saveField(field, required, active);
            return "redirect:/field";
        }
    }

    @PostMapping("/editfield")
    public String editField(Field field) {
        fieldRepository.save(field);
        return "redirect:/field";
    }

    private Field createField(Field field) {
        try {
            return fieldService.addNewField(field);
        } catch (LabelExistsException e) {
            return null;
        }
    }

    @GetMapping("/findById")
    @ResponseBody
    public Field findById(Integer idf) {
        return fieldRepository.findByIdf(idf);
    }

    @GetMapping("/delete")
    public String deleteField(Integer idf) {
        fieldRepository.deleteById(idf);
        return "redirect:/field";
    }
}

