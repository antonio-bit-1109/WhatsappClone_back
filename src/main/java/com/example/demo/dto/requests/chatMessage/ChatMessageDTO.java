package com.example.demo.dto.requests.chatMessage;

import com.example.demo.interfaces.DtoInterface;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class ChatMessageDTO implements DtoInterface {

    @NotNull(message = "userOwnerId must be not null")
    private Long userOwnerId;

    @NotEmpty(message = "text not null or empty")
    private String text;

    @NotEmpty(message = "chatIdentity must not be null")
    private String chatIdentity;

    private String username;


    public ChatMessageDTO(Long userOwnerId, String text, String chatIdentity, String username) {
        this.userOwnerId = userOwnerId;
        this.text = text;
        this.chatIdentity = chatIdentity;
        this.username = username;
    }

    public ChatMessageDTO() {
      
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChatIdentity() {
        return chatIdentity;
    }

    public void setChatIdentity(String chatIdentity) {
        this.chatIdentity = chatIdentity;
    }


    public Long getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(Long userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
