package com.example.demo.interfaces;

import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
import com.example.demo.entity.App_User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public interface IChatWebSocketService {

    //    UUID retriveUuidChat(ChatMessageDTO dataChat);
    void sendToPrivateChat(ChatMessageDTO messageDTO, App_User user, LocalDateTime sendAt);
}
