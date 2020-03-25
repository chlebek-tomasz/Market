package com.chlebek.project.repository.util;

import com.chlebek.project.model.util.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE (m.senderId=:senderId AND m.receiverId=:receiverId) OR (m.senderId=:receiverId AND m.receiverId=:senderId) ORDER BY m.sendDate ASC ")
    List<Message> find(Long senderId, Long receiverId);
}
