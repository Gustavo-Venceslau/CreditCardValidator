package com.galmv_.domain.exceptions.custom;

import java.io.Serial;

public class InvalidCreditCardFANException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    public InvalidCreditCardFANException(String message){
        super(message);
    }
}
