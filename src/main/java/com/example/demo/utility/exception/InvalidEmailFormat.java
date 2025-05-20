package com.example.demo.utility.exception;

public class InvalidEmailFormat extends RuntimeException {
    public InvalidEmailFormat(String message) {
        super(message);
    }
}
