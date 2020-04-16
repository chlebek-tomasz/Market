package com.chlebek.project.controller;

import com.chlebek.project.model.product.Product;
import com.chlebek.project.model.user.User;
import com.chlebek.project.model.util.Message;
import com.chlebek.project.service.UserService;
import com.chlebek.project.service.product.ProductService;
import com.chlebek.project.service.util.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Id;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessagingController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/{id}/sendmessage")
    public String sendMessage(@PathVariable("id") Long id, @ModelAttribute("message") Message message){
        messageService.save(message, id);
        return "redirect:/advert/{id}";
    }

    @GetMapping("/messages")
    public String showMessages(Model model){
        User user = userService.setUser();
        List<Message> messages = messageService.getMessagesToUser(user.getId());
        Map<User, String> messageMap = new HashMap<>();
        for(Message message : messages){
            if (!messageMap.containsKey(message.getSenderId())){
                messageMap.put(userService.getUserById(message.getSenderId()), message.getText());
            }
        }
        model.addAttribute("messages", messageMap);
        return "mailbox";
    }

    @GetMapping("/test")
    public String getTest(){
        return "header";
    }


}
