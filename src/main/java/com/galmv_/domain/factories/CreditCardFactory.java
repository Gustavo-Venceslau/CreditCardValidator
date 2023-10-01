package com.galmv_.domain.factories;

import com.galmv_.domain.entities.CreditCard;
import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.entities.AmericanExpress;
import com.galmv_.domain.entities.CommonCreditCard;
import com.galmv_.domain.exceptions.InvalidCreditCardTypeException;
import com.galmv_.domain.services.AmericanExpressCreditCardValidator;
import com.galmv_.domain.services.CommonCreditCardValidator;
import com.galmv_.domain.utils.ConvertExpiryDateToCalendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@RequiredArgsConstructor
public class CreditCardFactory {

    private final AmericanExpressCreditCardValidator americanExpressValidator;
    private final CommonCreditCardValidator commonCardValidator;

    public CreditCard getCreditCard(AddCreditCardDTO creditCard){
        Calendar expiryDate = ConvertExpiryDateToCalendar.convert(creditCard.expiryDate());

        if(creditCard.type() == CreditCardType.COMMON_CREDIT_CARD){
            commonCardValidator.validate(creditCard);

            return CommonCreditCard.CommonCreditBuilder()
                    .CVV(creditCard.CVV())
                    .PAN(creditCard.FAN())
                    .ownerName(creditCard.ownerName())
                    .expiryDate(expiryDate)
                    .build();
        }

        if(creditCard.type() == CreditCardType.AMERICAN_EXPRESS_CARD){
            americanExpressValidator.validate(creditCard);

            return AmericanExpress.AmericanExpressCardBuilder()
                    .CVV(creditCard.CVV())
                    .PAN(creditCard.FAN())
                    .ownerName(creditCard.ownerName())
                    .expiryDate(expiryDate)
                    .build();
        }

        throw new InvalidCreditCardTypeException(Errors.INVALID_CREDIT_CARD_TYPE);
    }
}
