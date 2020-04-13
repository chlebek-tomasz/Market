package com.chlebek.project.service.util;

import com.chlebek.project.model.util.Message;
import com.chlebek.project.repository.util.MessageRepository;
import com.chlebek.project.service.UserService;
import com.chlebek.project.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void save(Message message, Long id) {
        message.setSendDate(new Date());
        message.setSenderId(userService.setUser().getId());
        message.setReceiverId(productService.getProductById(id).getUser().getId());
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages(Long senderId, Long receiverId) {
        return messageRepository.find(senderId, receiverId);
    }

    @Override
    public List<Message> getMessagesToUser(Long receiverId) {
        return messageRepository.findReceive(receiverId);
    }


}
