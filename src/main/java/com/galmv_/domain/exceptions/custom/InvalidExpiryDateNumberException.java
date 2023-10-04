package com.galmv_.domain.exceptions.custom;

import java.io.Serial;

public class InvalidExpiryDateNumberException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidExpiryDateNumberException(String message){
        super(message);
    }
}
