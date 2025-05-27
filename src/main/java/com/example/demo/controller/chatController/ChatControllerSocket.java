package com.example.demo.controller.chatController;

import com.example.demo.dto.requests.chatMessage.ChatMessageDTO;
import com.example.demo.enums.BrokerDestinations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


// controller per la gestione dei messaggi webSocket
@Controller
public class ChatControllerSocket {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatControllerSocket(
            SimpMessagingTemplate simpMessagingTemplate
    ) {
        this.messagingTemplate = simpMessagingTemplate;
    }

    // messaggi inviati ad /app/send-private
    // gestione del messaggio tramite chat privata
    @MessageMapping("/send-private")
    public void sendPrivateMessage(@Payload ChatMessageDTO message) {

        // prendo il chatID dal payload e lo uso per trovare
        // il chat identity (UUID) per identificare/creare il canale a cui inviare il messaggio
        // sul socket
        // messaggio inviato tramite webSocket al canale che sarà "/send-private
        this.messagingTemplate.convertAndSend(BrokerDestinations.PRIVATE + message.getChatIdentity());
    }
}
