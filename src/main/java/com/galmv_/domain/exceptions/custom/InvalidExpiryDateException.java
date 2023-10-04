package com.galmv_.domain.exceptions.custom;

import org.springframework.validation.Errors;

import java.io.Serial;

public class InvalidExpiryDateException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidExpiryDateException(String message){
        super(message);
    }
}
