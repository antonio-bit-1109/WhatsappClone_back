package com.example.demo.dto.requests.messageMe;

import com.example.demo.interfaces.DtoInterface;
import jakarta.validation.constraints.NotBlank;

public class SendMeMessageDTO implements DtoInterface {

    @NotBlank(message = "il sender non può essere null or empty")
    private String sender;
    @NotBlank(message = "il sender non può essere null or empty")
    private String message;

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
