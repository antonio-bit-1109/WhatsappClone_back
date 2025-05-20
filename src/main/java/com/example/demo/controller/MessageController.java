package com.example.demo.controller;

import com.example.demo.dto.requests.messageMe.SendMeMessageDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.interfaces.ISendMessage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendMessage")
public class MessageController {

    private final ISendMessage sendMessage;

    public MessageController(ISendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }


    @PostMapping("/toMe")
    public ResponseEntity<StringResponse> sendMeAMessage(
            @Valid @RequestBody SendMeMessageDTO messageData
    ) {
        this.sendMessage.registerMessage(messageData);
        return ResponseEntity.ok(new StringResponse("messaggio salvato con successo."));
    }
}
