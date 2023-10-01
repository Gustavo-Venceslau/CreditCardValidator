package com.galmv_.domain.services;

import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.exceptions.InvalidCVVException;
import com.galmv_.domain.exceptions.InvalidCreditCardFANException;
import com.galmv_.domain.exceptions.InvalidExpiryDateException;
import com.galmv_.domain.utils.ConvertExpiryDateToCalendar;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class CommonCreditCardValidator {

    public boolean validate(AddCreditCardDTO creditCard){
        return validateCreditCardCVV(creditCard.CVV()) &&
                validateCreditCardPAN(creditCard.FAN()) &&
                validateCreditCardExpiryDate(creditCard.expiryDate());
    }

    private boolean validateCreditCardCVV(String cvv) {
        if(cvv.length() != 3){
            throw new InvalidCVVException(Errors.INVALID_CVV_LENGTH);
        }
        return true;
    }

    private boolean validateCreditCardPAN(String pan){
        if (pan.length() < 16 || pan.length() > 19){
            throw new InvalidCreditCardFANException(Errors.INVALID_CREDIT_CARD_FAN_LENGTH);
        }
        return true;
    }

    private boolean validateCreditCardExpiryDate(String expiryDate){
        Calendar today = Calendar.getInstance();
        Calendar convertedExpiryDate = ConvertExpiryDateToCalendar.convert(expiryDate);

        if(!convertedExpiryDate.after(today)){
            throw new InvalidExpiryDateException(Errors.INVALID_EXPIRY_DATE);
        }
        return true;
    }

}
