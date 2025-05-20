package com.example.demo.utility.exception;

public class BadTelephoneFormat extends RuntimeException {
    public BadTelephoneFormat(String message) {
        super(message);
    }
}
