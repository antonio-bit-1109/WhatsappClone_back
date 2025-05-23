package com.example.demo.controller.chatController;

import com.example.demo.dto.requests.chatMessage.CreateChatDTO;
import com.example.demo.dto.responses.ChatGetDTO;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.service.ChatRestService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chat/rest")
public class ChatControllerRest {

    private final ChatRestService chatRestService;

    public ChatControllerRest(ChatRestService chatRestService) {
        this.chatRestService = chatRestService;
    }

    /**
     * Handles the creation of a new chat. This endpoint accepts a list of
     * participant IDs and creates a chat using the provided data.
     *
     * @param partecipants an instance of {@code CreateChatDTO} containing the
     *                     list of participant IDs and related information
     *                     required to create the chat; must be valid.
     * @return a {@code ResponseEntity} containing a {@code StringResponse}
     * object with a success message.
     */
    @PostMapping("/create")
    public ResponseEntity<StringResponse> createChat(@Valid @RequestBody CreateChatDTO partecipants) {
        this.chatRestService.create(partecipants);
        return ResponseEntity.ok(new StringResponse("nuova chat creata con successo"));
    }

    @GetMapping("/get/all/mine/{userId}")
    public ResponseEntity<List<ChatGetDTO>> getAllMineChats(
            @NotNull @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.ok(
                this.chatRestService.getAll(Optional.of(userId))
        );
    }
}
