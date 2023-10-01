package com.galmv_.domain.utils;

import java.util.Calendar;

public class ConvertExpiryDateToCalendar {

    public static Calendar convert(String expiryDate){
        Calendar newExpiryCalendar = Calendar.getInstance();

        int year = 2000 + Integer.parseInt(expiryDate.substring(3, 5));
        int month = Integer.parseInt(expiryDate.substring(0, 2)) - 1;

        newExpiryCalendar.set(Calendar.YEAR, year);
        newExpiryCalendar.set(Calendar.MONTH, month);

        return newExpiryCalendar;
    }
}
