package com.example.demo.dto.requests;

import com.example.demo.interfaces.DtoInterface;

public class CreateUserDTO implements DtoInterface {

    private String username;
    private String password;

    public CreateUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
