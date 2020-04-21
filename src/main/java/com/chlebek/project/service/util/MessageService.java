package com.chlebek.project.service.util;

import com.chlebek.project.model.util.Message;

import java.util.List;

public interface MessageService {
    void save(Message message, Long productId);

    void saveFromUser(Message message, Long userId);

    List<Message> getMessages(Long senderId, Long receiverId);

    List<Message> getMessagesToUser(Long receiverId);
}
