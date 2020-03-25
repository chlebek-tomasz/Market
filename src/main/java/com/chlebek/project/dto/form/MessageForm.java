package com.chlebek.project.dto.form;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class MessageForm {
    @NotNull
    private String message;
    private Long senderId;
    private Long receiverId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
