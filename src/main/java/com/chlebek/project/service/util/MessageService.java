package com.chlebek.project.service.util;

import com.chlebek.project.model.util.Message;

import java.util.List;

public interface MessageService {
    void save(Message message, Long id);

    List<Message> getMessages(Long senderId, Long receiverId);

    List<Message> getMessagesToUser(Long receiverId);
}
