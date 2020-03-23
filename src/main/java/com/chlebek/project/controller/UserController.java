package com.chlebek.project.controller;

import com.chlebek.project.model.user.User;
import com.chlebek.project.service.UserService;
import dto.form.EmailForm;
import dto.form.PasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        User user = userService.setUser();
        model.addAttribute("email", user.getEmail());
        model.addAttribute("firstName", user.getFirstName());
        return "dashboard";
    }

    @GetMapping("/profile")
    public String showMyProfile(Model model){
        User user = userService.setUser();
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/changepassword")
    public String changePasswordForm(Model model){
        model.addAttribute("passwordForm", new PasswordForm());
        return "changepassword";
    }

    @PostMapping("/profile/changepassword")
    public String changePassword(@Valid @ModelAttribute PasswordForm passwordForm, BindingResult result, Model model) {
        User user = userService.setUser();
        if (!passwordForm.getPassword().equals(passwordForm.getPasswordMatches())){
            result.rejectValue("password", "error.user", "Passwords don't match");
            return "changepassword";
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(passwordForm.getPassword()));
            userService.updateUser(user);
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/changeemail")
    public String changeEmailForm(Model model){
        model.addAttribute("emailForm", new EmailForm());
        return "changeemail";
    }

    @PostMapping("/profile/changeemail")
    public String changeEmail(@Valid @ModelAttribute EmailForm emailForm, BindingResult result, Model model){
        User user = userService.setUser();
        if(!emailForm.getEmail().equals(emailForm.getEmailConfirm())){
            result.rejectValue("email", "error.user", "Emails don't match");
            return "changeemail";
        }else {
            user.setEmail(emailForm.getEmail());
            userService.updateUser(user);
        }
        return "login";
    }


    @PostMapping("/profile/update")
    public String updateMyProfile(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        String toReturn = null;
        if (result.hasErrors()) {
            result.rejectValue("password", "error.user", "Passwords don't match");
            return "profileupdate";
        } else {
            User toUpdate = userService.setUser();
            if(toUpdate.getEmail().equals(user.getEmail())){
                toReturn = "redirect:/profile";
            } else {
                toReturn = "redirect:/login";
                toUpdate.setEmail(user.getEmail());
            }
            toUpdate.setFirstName(user.getFirstName());
            toUpdate.setLastName(user.getLastName());
            toUpdate.setPhoneNumber(user.getPhoneNumber());
            userService.updateUser(toUpdate);
            return toReturn;
        }

    }

}
