package com.chlebek.project.controller;

import com.chlebek.project.model.util.Message;
import com.chlebek.project.service.UserService;
import com.chlebek.project.service.product.ProductService;
import com.chlebek.project.service.util.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Id;
import java.util.Date;

@Controller
public class MessagingController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/{id}/sendmessage")
    public String sendMessage(@PathVariable("id") Long id, @ModelAttribute("message") Message message){
        messageService.save(message, id);
        return "redirect:/advert/{id}";
    }

    @GetMapping("/test")
    public String getTest(){
        return "header";
    }
}
