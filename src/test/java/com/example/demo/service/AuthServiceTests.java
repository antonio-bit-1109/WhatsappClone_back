package com.example.demo.service;

import com.example.demo.dto.requests.appUser.CreateAnagraficaDTO;
import com.example.demo.dto.requests.appUser.CreateUserDTO;
import com.example.demo.dto.requests.appUser.UserRegistrationDTO;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.repository.AnagraficaRepository;
import com.example.demo.repository.App_UserRepository;
import com.example.demo.utility.factory.entityfactory.Factory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {

    @Mock
    private App_UserRepository appUserRepository;

    @Mock
    private AnagraficaRepository anagraficaRepository;

    @Mock
    private Factory factory;

    @InjectMocks
    private AuthService authService;

    @Test
    public void creationUserSuccess() {

        UserRegistrationDTO dto = new UserRegistrationDTO(
                "username",
                "password",
                "nome",
                "cognome",
                "rzzntn95p11h501q",
                LocalDateTime.of(1995, 9, 11, 0, 0), // data di nascita: 11/09/1995
                "Roma", // luogo di nascita
                "3331234567",
                "defaultemail@prova.it"// telefono
        );

        App_User userMock = new App_User();
        Anagrafica anagMock = new Anagrafica();

        when(factory.createEntityUser(any(CreateUserDTO.class), anyBoolean())).thenReturn(userMock);

        when(this.factory.createEntityAnagrafica(any(CreateAnagraficaDTO.class)))
                .thenReturn(anagMock);

        when(this.factory.addAnagraficaToUser(any(Anagrafica.class), any(App_User.class)))
                .thenReturn(userMock);

        this.authService.create(dto);

        verify(this.anagraficaRepository, times(1)).save(any(Anagrafica.class));
        verify(this.appUserRepository, times(1)).save(any(App_User.class));
    }

}
