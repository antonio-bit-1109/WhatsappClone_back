package com.example.demo.enums;

public enum ProfileImage {

    DEFAULT("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");

    private String src;

    ProfileImage(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }
}
