package com.galmv_.domain.factories;

import com.galmv_.domain.entities.CreditCard;
import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.entities.AmericanExpress;
import com.galmv_.domain.entities.CommonCreditCard;
import com.galmv_.domain.enums.CreditCardType;
import com.galmv_.domain.exceptions.custom.InvalidCreditCardTypeException;
import com.galmv_.domain.utils.ConvertExpiryDateToLocalDate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreditCardFactory {

    public CreditCard getCreditCard(AddCreditCardDTO creditCard){
        LocalDate expiryDate = ConvertExpiryDateToLocalDate.convert(creditCard.expiryDate());

        if(CreditCardType.valueOf(creditCard.type()) == CreditCardType.COMMON_CREDIT_CARD){
            CreditCard card = new CommonCreditCard();
            card.validateCreditCard(creditCard);

            return CommonCreditCard.CommonCreditBuilder()
                    .CVV(creditCard.CVV())
                    .FAN(creditCard.FAN())
                    .ownerName(creditCard.ownerName())
                    .expiryDate(expiryDate)
                    .build();
        }

        if(CreditCardType.valueOf(creditCard.type()) == CreditCardType.AMERICAN_EXPRESS_CARD){
            CreditCard card = new AmericanExpress();
            card.validateCreditCard(creditCard);

            return AmericanExpress.AmericanExpressCardBuilder()
                    .CVV(creditCard.CVV())
                    .FAN(creditCard.FAN())
                    .ownerName(creditCard.ownerName())
                    .expiryDate(expiryDate)
                    .build();
        }

        throw new InvalidCreditCardTypeException(Errors.INVALID_CREDIT_CARD_TYPE);
    }
}
