package com.example.demo.dto.responses;

import com.example.demo.interfaces.DtoInterface;

public class ExtraMinimalUserInfo implements DtoInterface {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String profilePhoto;

    public ExtraMinimalUserInfo() {
    }

    public ExtraMinimalUserInfo(Long id,
                                String name,
                                String surname,
                                String username,
                                String profilePhoto
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.profilePhoto = profilePhoto;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
