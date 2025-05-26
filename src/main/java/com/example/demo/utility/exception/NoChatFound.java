package com.example.demo.utility.exception;

public class NoChatFound extends RuntimeException {
    public NoChatFound(String message) {
        super(message);
    }
}
