package com.example.demo.utility.exception;

import com.example.demo.dto.responses.ErrorResponse;
import com.example.demo.dto.responses.StringResponse;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// rest controller per la gestione centralizzata degli errori
//NB implementando 'ResponseEntityExceptionHandler' è gia presente un gestore
// per gli errori di tipo 'MethodArgumentNotValidException' che sarebbe l eccezzione generata
// quando i validatori all interno delle dto falliscono @NotNUll @Email @NotEmpty ecc ecc
// quindi essendo gia presente di fedault non c'è bisogno di specificarlo.
@ControllerAdvice
public class ControllerResponseExceptions extends ResponseEntityExceptionHandler {


    // acchiappare gli errori generati dai validators nelle dto MethodArgumentNotValidException
    // farne l override perche gia presente di default nella classe estesa, sovrascrivere
    // con logica personalizzata
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }


    // intercettazione degli errori runtime
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(
            RuntimeException ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(true),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // se viene lanciato errore alla login (custom errore)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialException(
            InvalidCredentialsException ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransientObjectException.class)
    public ResponseEntity<ErrorResponse> handleTransientException(
            TransientObjectException ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //exception generata quando si viola una constrain del database (campi unique o chiavi non rispettate)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // se viene lanciata Exception user not found
    // gestita da qui
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFound ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // impossibilità di salvare un entity gia presente sul db
    @ExceptionHandler(InvocationTargetException.class)
    public ResponseEntity<ErrorResponse> handleInvocationTargetException(
            InvocationTargetException ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.BAD_REQUEST);
    }


    // stai tentando di craere un admin con una username che gia esiste, non possibile.
    @ExceptionHandler(AdminAlreadyCreated.class)
    public ResponseEntity<ErrorResponse> handleAdminAlreadyCreated(
            AdminAlreadyCreated ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidEmailFormat.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmailFormat(
            InvalidEmailFormat ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadWordFounded.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmailFormat(
            BadWordFounded ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataTemporalFormat.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmailFormat(
            InvalidDataTemporalFormat ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadTelephoneFormat.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmailFormat(
            BadTelephoneFormat ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoChatFound.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmailFormat(
            NoChatFound ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(new ErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        ),
                HttpStatus.BAD_REQUEST);
    }
}
