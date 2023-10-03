package com.galmv_.domain.exceptions;

import java.io.Serial;

public class InvalidCreditCardTypeException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidCreditCardTypeException(String message){ super(message); }
}
