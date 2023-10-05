package com.galmv_.domain.services;

import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.exceptions.custom.InvalidCVVException;
import com.galmv_.domain.exceptions.custom.InvalidCreditCardFANException;
import com.galmv_.domain.exceptions.custom.InvalidExpiryDateException;
import com.galmv_.domain.utils.ConvertExpiryDateToLocalDate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AmericanExpressCreditCardValidator {

    public boolean validate(AddCreditCardDTO creditCard){
        return validateCreditCardCVV(creditCard.CVV()) &&
                validateCreditCardFANStartsWith(creditCard.FAN()) &&
                validateCreditCardExpiryDate(creditCard.expiryDate());
    }

    private boolean validateCreditCardCVV(String cvv) {
        if(cvv.length() != 4){
            throw new InvalidCVVException(Errors.INVALID_CVV_LENGTH);
        }
        return true;
    }

    private boolean validateCreditCardFANStartsWith(String fan) {
        if(!(fan.startsWith("34") || fan.startsWith("37"))) {
            throw new InvalidCreditCardFANException(Errors.INVALID_CREDIT_CARD_FAN_NUMBER);
        }
        return validateFANNumberIsCorrect(fan);
    }

    // Luhn algorithm implementation
    private boolean validateFANNumberIsCorrect(String fan) {
        int digitsSum = 0;
        boolean isSecond = false;

        for(int index = fan.length() - 1; index >= 0; index--){
            int digit = fan.charAt(index) - '0';

            if(isSecond) digit *= 2;

            digitsSum += digit / 10;
            digitsSum += digit % 10;

            isSecond = !isSecond;
        }

        System.out.println(digitsSum);

        if(digitsSum % 10 != 0)
            throw new InvalidCreditCardFANException(Errors.INVALID_CREDIT_CARD_FAN_NUMBER);

        return true;
    }

    private boolean validateCreditCardExpiryDate(String expiryDate){
        LocalDate today = LocalDate.now();

        LocalDate convertedExpiryDate = ConvertExpiryDateToLocalDate.convert(expiryDate);

        System.out.println(convertedExpiryDate);

        if(!convertedExpiryDate.isAfter(today)){
            throw new InvalidExpiryDateException(Errors.INVALID_EXPIRY_DATE);
        }
        return true;
    }
}
