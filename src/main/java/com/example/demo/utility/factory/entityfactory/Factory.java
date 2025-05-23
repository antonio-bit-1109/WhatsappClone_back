package com.example.demo.utility.factory.entityfactory;

import com.example.demo.dto.requests.appUser.CreateAnagraficaDTO;
import com.example.demo.dto.requests.appUser.CreateUserDTO;
import com.example.demo.dto.requests.chatMessage.CreateChatDTO;
import com.example.demo.dto.requests.messageMe.SendMeMessageDTO;
import com.example.demo.entity.*;
import com.example.demo.enums.ProfileImage;
import com.example.demo.service.AuthService;
import com.example.demo.service.ChatRestService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;


@Service
public class Factory implements EntityFactory {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final ChatRestService chatRestService;

    public Factory(PasswordEncoder passwordEncoder,
                   @Lazy AuthService authService,
                   @Lazy ChatRestService chatRestService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.chatRestService = chatRestService;
    }

    @Override
    public App_User createEntityUser(CreateUserDTO data, boolean isRegisteringAdmin) {
        App_User user = new App_User();
        user.setUsername(data.getUsername());
        user.setPassword(this.passwordEncoder.encode(data.getPassword()));
        user.setAccountNotExpired(true);
        user.setAccountNotLocked(true);
        user.setIsEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setEmail(data.getEmail());
        user.setProfileImage(ProfileImage.DEFAULT.getSrc());
        if (isRegisteringAdmin) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }
        return user;
    }

    @Override
    public Anagrafica createEntityAnagrafica(CreateAnagraficaDTO data) {
        Anagrafica a = new Anagrafica();
        a.setNome(data.getNome());
        a.setCognome(data.getCognome());
        a.setCf(data.getCf());
        a.setDataNascita(data.getDataNascita());
        a.setLuogoDiNascita(data.getLuogoNascita());
        a.setTelefono(data.getTelefono());
        a.setUser(data.getUser());
        return a;
    }

    @Override
    public StorageLogs createEntityStorageLog(
            String timeStamp,
            String logType,
            Integer processId,
            String message,
            String threadName
    ) {

        ZonedDateTime zdt = ZonedDateTime.parse(timeStamp);
        return new StorageLogs(threadName, message, processId, logType, zdt);
    }

    @Override
    public StoreMessages createEntityStoreMessage(SendMeMessageDTO data) {
        return new StoreMessages(data.getSender(), data.getMessage());
    }

    @Override
    public Chat createEntityChat(CreateChatDTO data) {
        Chat c = new Chat();
        c.setCreatedAt(LocalDateTime.now());
        c.setIdentity(UUID.randomUUID());
        c.setListaMessaggi(new ArrayList<>());
        c.setParticipants(new ArrayList<>());
        Chat saved = this.chatRestService.save(c);
        data.getListaPartecipanti().forEach(idUser -> {
            c.addIntoPartecipantsList(this.authService.getUserById(idUser));
            addChatToUser(idUser, saved);
        });
        return saved;
    }

    public App_User addAnagraficaToUser(
            Anagrafica anagrafica,
            App_User user
    ) {
        user.setAnagrafica(anagrafica);
        return user;
    }


    public void addChatToUser(Long idUser, Chat chat) {
        App_User user = this.authService.getUserById(idUser);
        if (user.getListaChats() == null) {
            user.setListaChats(new ArrayList<>());
        }
        user.getListaChats().add(chat);
        this.authService.save(user);
    }
}
