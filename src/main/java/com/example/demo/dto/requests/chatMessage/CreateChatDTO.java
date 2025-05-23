package com.example.demo.dto.requests.chatMessage;

import com.example.demo.interfaces.DtoInterface;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CreateChatDTO implements DtoInterface {

    @Size(min = 2, message = "La lista deve contenere almeno 2 partecipanti")
    private List<Long> listaPartecipanti;

    public void setListaPartecipanti(List<Long> listaPartecipanti) {
        this.listaPartecipanti = listaPartecipanti;
    }

    public List<Long> getListaPartecipanti() {
        return listaPartecipanti;
    }
}
