package com.example.demo.utility.factory;

import com.example.demo.dto.requests.appUser.CreateAnagraficaDTO;
import com.example.demo.dto.requests.appUser.CreateUserDTO;
import com.example.demo.dto.requests.messageMe.SendMeMessageDTO;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.entity.StorageLogs;
import com.example.demo.entity.StoreMessages;
import com.example.demo.enums.ProfileImage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;


@Service
public class Factory implements EntityFactory {

    private final PasswordEncoder passwordEncoder;

    public Factory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

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


    public App_User addAnagraficaToUser(
            Anagrafica anagrafica,
            App_User user
    ) {
        user.setAnagrafica(anagrafica);
        return user;
    }


}
