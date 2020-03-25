package com.chlebek.project.service.util;

import com.chlebek.project.model.util.Message;

import java.util.List;

public interface MessageService {
    void save(Message message);

    List<Message> getMessages(Long senderId, Long receiverId);
}
