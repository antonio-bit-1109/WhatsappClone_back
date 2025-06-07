package com.example.demo.controller;

import com.example.demo.dto.requests.messageMe.ReplayMessageDTO;
import com.example.demo.dto.requests.messageMe.SendMeMessageDTO;
import com.example.demo.dto.responses.StoredMessageDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.interfaces.ISendMessage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sendMessage")
public class SendMeMessageEmailController {

    private final ISendMessage sendMessage;

    public SendMeMessageEmailController(ISendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    @PostMapping("/toMe")
    public ResponseEntity<StringResponse> sendMeAMessage(
            @Valid @RequestBody SendMeMessageDTO messageData
    ) {
        this.sendMessage.registerMessage(messageData);
        return ResponseEntity.ok(new StringResponse("messaggio salvato con successo."));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<StoredMessageDTO>> getAllMessagesFromPeople() {
        return ResponseEntity.ok(this.sendMessage.getAllEmailFromPeople());
    }


    @PostMapping("/replay")
    public ResponseEntity<StringResponse> replayToMessage(@Valid @RequestBody ReplayMessageDTO message) {
        return ResponseEntity.ok(this.sendMessage.replayToMessage(message));
    }

}
