package com.example.demo.dto.responses;

import com.example.demo.enums.MessageStatus;
import com.example.demo.interfaces.DtoInterface;

import java.time.LocalDateTime;

public class MessaggioDto implements DtoInterface {
    private LocalDateTime sendAtTime;
    private MessageStatus messageStatus;
    private String content;
    private String userSender;
    private String email;


    public MessaggioDto(LocalDateTime sendAtTime, MessageStatus messageStatus, String content, String userSender, String email) {
        this.sendAtTime = sendAtTime;
        this.messageStatus = messageStatus;
        this.content = content;
        this.userSender = userSender;
        this.email = email;
    }

    public MessaggioDto() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUserSender(String userSender) {
        this.userSender = userSender;
    }

    public String getUserSender() {
        return userSender;
    }

    public LocalDateTime getSendAtTime() {
        return sendAtTime;
    }

    public void setSendAtTime(LocalDateTime sendAtTime) {
        this.sendAtTime = sendAtTime;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
