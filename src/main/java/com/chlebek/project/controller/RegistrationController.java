package com.chlebek.project.controller;

import com.chlebek.project.exception.EmailExistsException;
import com.chlebek.project.model.user.User;
import com.chlebek.project.model.user.VerificationForm;
import com.chlebek.project.model.user.VerificationToken;
import com.chlebek.project.service.UserService;
import com.chlebek.project.dto.UserRegistrationDto;
import com.chlebek.project.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private VerificationTokenService verificationTokenService;

    @GetMapping
    public String registration(Model model, HttpSession session) {
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
                verificationTokenService.createVerification(user.getEmail());
            } catch (EmailExistsException ee) {
                result.rejectValue("email", "error.user", "There is an account with that email adress");
                return "registration";
            }

            return "registration-verify";
        }
    }

    @GetMapping("/verify-email")
    @ResponseBody
    public String verifyEmail(String code) {
        return verificationTokenService.verifyEmail(code).getBody();
    }
}

