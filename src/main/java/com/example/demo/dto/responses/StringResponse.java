package com.example.demo.dto.responses;

public class StringResponse {

    private String message;

    public StringResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
