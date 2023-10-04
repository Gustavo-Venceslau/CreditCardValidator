package com.galmv_.domain.exceptions.custom;

import java.io.Serial;

public class CreditCardAlreadyExistsException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public CreditCardAlreadyExistsException(String message){
        super(message);
    }
}
