package com.example.demo.dto.requests.appUser;

import com.example.demo.interfaces.DtoInterface;
import jakarta.validation.constraints.NotNull;

public class LoginDTO implements DtoInterface {

    @NotNull(message = "lo username non può essere null.")
    private String username;

    @NotNull(message = "la password non può essere null.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
