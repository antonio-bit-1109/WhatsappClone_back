package com.example.demo.dto.requests.messageMe;

import com.example.demo.interfaces.DtoInterface;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ReplayMessageDTO implements DtoInterface {

    //    @NotEmpty(message = "email necessaria.")
//    @Email(message = "formato email non valido.")
//    private String emailSender;
    @NotEmpty(message = "messaggio necessario.")
    private String replayMessage;
    @NotEmpty(message = "oggetto email necessario.")
    private String object;
    @NotNull(message = "id non può essere null.")
    private Long idStoredMessage;

    public Long getIdStoredMessage() {
        return idStoredMessage;
    }

    public void setIdStoredMessage(Long idStoredMessage) {
        this.idStoredMessage = idStoredMessage;
    }
    

    public String getReplayMessage() {
        return replayMessage;
    }

    public void setReplayMessage(String replayMessage) {
        this.replayMessage = replayMessage;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
