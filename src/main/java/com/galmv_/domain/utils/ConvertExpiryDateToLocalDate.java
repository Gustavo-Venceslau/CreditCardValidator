package com.galmv_.domain.utils;

import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.exceptions.custom.InvalidExpiryDateNumberException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertExpiryDateToLocalDate {

    public static LocalDate convert(String expiryDate){
        if(expiryDate.startsWith("00", 5)){
            throw new InvalidExpiryDateNumberException(Errors.INVALID_EXPIRY_DATE_NUMBER);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate newExpiryDate = LocalDate.parse(expiryDate, formatter);

        return newExpiryDate;
    }
}
