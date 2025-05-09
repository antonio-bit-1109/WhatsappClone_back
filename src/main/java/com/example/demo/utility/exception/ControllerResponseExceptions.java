package com.example.demo.utility.exception;

import com.example.demo.dto.responses.StringResponse;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


// rest controller per la gestione centralizzata degli errori
@RestControllerAdvice
public class ControllerResponseExceptions {


    // intercettazione degli errori runtime
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StringResponse> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(new StringResponse(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // intercettazione quando un validator non viene rispettato (il @Valid  fallisce)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put("message", error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // se viene lanciato errore alla login (custom errore)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<StringResponse> handleInvalidCredentialException(InvalidCredentialsException ex){
        return new ResponseEntity<>(new StringResponse(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransientObjectException.class)
    public ResponseEntity<StringResponse> handleTransientException(TransientObjectException ex){
        return new ResponseEntity<>(new StringResponse(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //exception generata quando si viola una constrain del database (campi unique o chiavi non rispettate)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StringResponse> handleConstraintViolation(ConstraintViolationException ex){
        return new ResponseEntity<>(new StringResponse(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
