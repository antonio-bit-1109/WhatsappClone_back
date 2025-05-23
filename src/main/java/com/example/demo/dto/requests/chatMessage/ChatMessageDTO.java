package com.example.demo.dto.requests.chatMessage;

import com.example.demo.interfaces.DtoInterface;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class ChatMessageDTO implements DtoInterface {

    @NotNull(message = "userOwnerId must be not null")
    private Long UserOwnerId;
    @NotEmpty(message = "text not null or empty")
    private String text;
    @NotNull(message = "chatIdentity must not be null")
    private Long chatId;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getUserOwnerId() {
        return UserOwnerId;
    }

    public void setUserOwnerId(Long userOwnerId) {
        UserOwnerId = userOwnerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
