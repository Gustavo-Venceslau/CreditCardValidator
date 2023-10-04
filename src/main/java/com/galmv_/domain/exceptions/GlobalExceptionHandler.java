package com.galmv_.domain.exceptions;

import com.galmv_.domain.exceptions.custom.CreditCardAlreadyExistsException;
import com.galmv_.domain.exceptions.custom.InvalidCVVException;
import com.galmv_.domain.exceptions.custom.InvalidExpiryDateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handlerValidException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(
                objectError -> {
                    FieldError fieldError = (FieldError) objectError;
                    errors.put(fieldError.getField(), objectError.getDefaultMessage());
                }
        );

        return new ResponseEntity<>(
                new ExceptionResponse(
                    "Bad Request! consult the documentation",
                    LocalDateTime.now().toString(),
                    HttpStatus.BAD_REQUEST.value(),
                    exception.getClass().toString(),
                    errors
                ), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidExpiryDateException.class)
    public ResponseEntity<ExceptionResponse> handlerInvalidExpiryDateException(InvalidExpiryDateException exception){
        Map<String, String> errors = new HashMap<>();

        errors.put("expiryDate", exception.getMessage());

        return new ResponseEntity<>(
                new ExceptionResponse(
                        "Bad Request! consult the documentation",
                        LocalDateTime.now().toString(),
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getClass().toString(),
                        errors
                ), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidCVVException.class)
    public ResponseEntity<ExceptionResponse> handlerInvalidCVVException(InvalidCVVException exception){
        Map<String, String> errors = new HashMap<>();

        errors.put("CVV", exception.getMessage());

        return new ResponseEntity<>(
                new ExceptionResponse(
                        "Bad Request! consult the documentation",
                        LocalDateTime.now().toString(),
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getClass().toString(),
                        errors
                ), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CreditCardAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handlerCreditCardAlreadyExistsException(CreditCardAlreadyExistsException exception){
        Map<String, String> errors = new HashMap<>();

        errors.put("CreditCard", exception.getMessage());

        return new ResponseEntity<>(
                new ExceptionResponse(
                        "Bad Request! consult the documentation",
                        LocalDateTime.now().toString(),
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getClass().toString(),
                        errors
                ), HttpStatus.BAD_REQUEST
        );
    }
}
