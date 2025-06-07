package com.example.demo.utility.exception;

public class StoreMessageNotFound extends RuntimeException {
    public StoreMessageNotFound(String message) {
        super(message);
    }
}
