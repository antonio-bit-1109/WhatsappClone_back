package com.example.demo.interfaces;

import com.example.demo.dto.requests.appUser.LoginDTO;
import com.example.demo.dto.requests.appUser.UserRegistrationDTO;
import com.example.demo.dto.responses.ExtraMinimalUserInfo;
import com.example.demo.dto.responses.StringResponse;
import com.example.demo.entity.App_User;

import java.util.List;

// questa altra interfaccia implementerà gli altri metodi del servizio
// che non sono le 5 crud di base
// create, edit, delete, get , getAll
public interface IAuthService {
    StringResponse login(LoginDTO dataLogin);

    boolean userExistByUsername(String username);

    boolean passwordMatch(String rawPassword, String username);

    App_User getUserByUsername(String username);

    App_User getUserById(Long idUser);

    List<ExtraMinimalUserInfo> getAllUsersInoHaveChatWith(Long idUser);

    boolean userAlreadyInactive(App_User user);

    boolean checkIfIsRegisteringAdmin(UserRegistrationDTO data);

    boolean checkIfThisAdminAlreadyCreated(String username);

    App_User save(App_User userEntity);
}
