package com.example.demo.utility;

import com.example.demo.dto.responses.StringResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// rest controller per la gestione centralizzata degli errori
@RestControllerAdvice
public class ControllerResponseExceptions {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StringResponse> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(new StringResponse(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
