package com.chlebek.project.service.util;

import com.chlebek.project.model.util.Message;
import com.chlebek.project.repository.util.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages(Long senderId, Long receiverId) {
        return messageRepository.find(senderId, receiverId);
    }
}
