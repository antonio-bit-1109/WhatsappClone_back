package com.example.demo.utility.exception;

public class EmailAlreadyPresentInDb extends RuntimeException {
    public EmailAlreadyPresentInDb(String message) {
        super(message);
    }
}
