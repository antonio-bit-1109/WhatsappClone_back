package com.example.demo.utility.exception;

public class ErrorSendEmail extends RuntimeException {
  public ErrorSendEmail(String message) {
    super(message);
  }
}
