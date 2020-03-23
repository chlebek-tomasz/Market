package com.chlebek.project.controller;

import com.chlebek.project.exception.EmailExistsException;
import com.chlebek.project.model.user.User;
import com.chlebek.project.repository.UserRepository;
import com.chlebek.project.service.UserService;
import dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        return "registration";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDto user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            result.rejectValue("password", "error.user", "Passwords don't match");
            return "registration";
        } else {
            User registered = null;
            try {
                registered = userService.addUser(user);
            } catch (EmailExistsException ee) {
                result.rejectValue("email", "error.user", "There is an account with that email adress");
                return "registration";
            }

            model.addAttribute("user", user);

            return "login";
        }
    }
}
