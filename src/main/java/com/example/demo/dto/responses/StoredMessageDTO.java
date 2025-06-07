package com.example.demo.dto.responses;

import com.example.demo.interfaces.DtoInterface;

import java.time.LocalDateTime;

public class StoredMessageDTO implements DtoInterface {

    private Long id;
    private String emailSender;
    private boolean haveReplied;
    private String contentMsg;
    private LocalDateTime receivedAt;


    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public boolean isHaveReplied() {
        return haveReplied;
    }

    public void setHaveReplied(boolean haveReplied) {
        this.haveReplied = haveReplied;
    }

    public String getContentMsg() {
        return contentMsg;
    }

    public void setContentMsg(String contentMsg) {
        this.contentMsg = contentMsg;
    }
}
