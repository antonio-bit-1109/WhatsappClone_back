package com.example.demo.utility.exception;

public class AdminAlreadyCreated extends RuntimeException {
    public AdminAlreadyCreated(String message) {
        super(message);
    }
}
