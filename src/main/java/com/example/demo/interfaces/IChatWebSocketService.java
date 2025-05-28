package com.example.demo.interfaces;

import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
import org.springframework.stereotype.Service;


@Service
public interface IChatWebSocketService {

    //    UUID retriveUuidChat(ChatMessageDTO dataChat);
    void sendToPrivateChat(ChatMessageDTO messageDTO);
}
