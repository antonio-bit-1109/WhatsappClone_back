package com.example.demo.dto.responses;

import com.example.demo.interfaces.BaseResponse;

public class StringResponse implements BaseResponse {

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
