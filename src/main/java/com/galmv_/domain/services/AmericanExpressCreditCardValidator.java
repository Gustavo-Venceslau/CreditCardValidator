package com.galmv_.domain.services;

import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.exceptions.custom.InvalidCVVException;
import com.galmv_.domain.exceptions.custom.InvalidCreditCardFANException;
import com.galmv_.domain.exceptions.custom.InvalidExpiryDateException;
import com.galmv_.domain.utils.ConvertExpiryDateToLocalDate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;

@Service
public class AmericanExpressCreditCardValidator {

    public boolean validate(AddCreditCardDTO creditCard){
        return validateCreditCardCVV(creditCard.CVV()) &&
                validateCreditCardPAN(creditCard.FAN()) &&
                validateCreditCardExpiryDate(creditCard.expiryDate());
    }

    private boolean validateCreditCardCVV(String cvv) {
        if(cvv.length() != 4){
            throw new InvalidCVVException(Errors.INVALID_CVV_LENGTH);
        }
        return true;
    }

    private boolean validateCreditCardPAN(String pan){
        if(!(validateCreditCardFANLength(pan) &&
                validateCreditCardPANStartsWith(pan))
        )
        {
            throw new InvalidCreditCardFANException(Errors.INVALID_CREDIT_CARD_FAN_LENGTH);
        }
        return true;
    }

    private boolean validateCreditCardFANLength(String pan) {
        return pan.length() >= 16 && pan.length() <= 19;
    }

    private boolean validateCreditCardPANStartsWith(String pan) {
        return pan.startsWith("34") || pan.startsWith("37");
    }

    private boolean validateCreditCardExpiryDate(String expiryDate){
        LocalDate today = LocalDate.now();

        LocalDate convertedExpiryDate = ConvertExpiryDateToLocalDate.convert(expiryDate);

        if(!convertedExpiryDate.isAfter(today)){
            throw new InvalidExpiryDateException(Errors.INVALID_EXPIRY_DATE);
        }
        return true;
    }

}
