package com.example.demo.dto.responses;

import com.example.demo.interfaces.DtoInterface;

public class StoredMessageDTO implements DtoInterface {

    private String emailSender;
    private boolean haveReplied;
    private String contentMsg;

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
