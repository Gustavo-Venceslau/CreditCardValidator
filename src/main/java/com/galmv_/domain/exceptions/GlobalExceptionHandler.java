package com.galmv_.domain.exceptions;

import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.exceptions.custom.CreditCardAlreadyExistsException;
import com.galmv_.domain.exceptions.custom.InvalidCVVException;
import com.galmv_.domain.exceptions.custom.InvalidExpiryDateException;
import com.galmv_.domain.exceptions.custom.InvalidExpiryDateNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
                    "Incorrect information, please enter a valid credit card!",
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
                        Errors.INVALID_EXPIRY_DATE,
                        LocalDateTime.now().toString(),
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getClass().toString(),
                        errors
                ), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidExpiryDateNumberException.class)
    public ResponseEntity<ExceptionResponse> handlerDateTimeParseException(InvalidExpiryDateNumberException exception){
        Map<String, String> errors = new HashMap<>();

        errors.put("expiryDate", exception.getMessage());

        return new ResponseEntity<>(
                new ExceptionResponse(
                        Errors.INVALID_EXPIRY_DATE_NUMBER,
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
                        Errors.INVALID_CVV_LENGTH,
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
                        Errors.CREDIT_CARD_ALREADY_EXITS,
                        LocalDateTime.now().toString(),
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getClass().toString(),
                        errors
                ), HttpStatus.BAD_REQUEST
        );
    }
}
