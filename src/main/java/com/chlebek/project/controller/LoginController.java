package com.chlebek.project.controller;

import com.chlebek.project.model.user.User;
import com.chlebek.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/login"})
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String login() {
        return "login";
    }

}
