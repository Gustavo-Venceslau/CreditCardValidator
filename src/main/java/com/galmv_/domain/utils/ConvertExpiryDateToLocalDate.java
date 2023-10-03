package com.galmv_.domain.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertExpiryDateToLocalDate {

    public static LocalDate convert(String expiryDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newExpiryDate = LocalDate.parse(expiryDate, formatter);

        return newExpiryDate;
    }
}
