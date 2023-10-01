package com.galmv_.domain.exceptions;

import java.io.Serial;

public class InvalidCVVException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidCVVException(String message){
        super(message);
    }

}
