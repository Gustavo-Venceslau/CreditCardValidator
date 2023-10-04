package com.galmv_.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
}
