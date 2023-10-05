package creditCardValidatition.factories;

import com.galmv_.domain.entities.CreditCard;
import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.entities.AmericanExpress;
import com.galmv_.domain.entities.CommonCreditCard;
import com.galmv_.domain.exceptions.custom.InvalidCreditCardTypeException;
import com.galmv_.domain.enums.CreditCardType;

import java.time.LocalDate;
import java.util.UUID;

public class CreditCardFactory {

    public static CreditCard getCreditCard(CreditCardType type){
        LocalDate expiryDate = LocalDate.now().plusYears(5);;

        if(type == CreditCardType.COMMON_CREDIT_CARD){
            return CommonCreditCard.CommonCreditBuilder()
                    .id(UUID.randomUUID())
                    .CVV("123")
                    .FAN("3440215794378998")
                    .ownerName("Gustavo de Almeida")
                    .expiryDate(expiryDate)
                    .build();
        }
        else  if(type == CreditCardType.AMERICAN_EXPRESS_CARD){
            return AmericanExpress.AmericanExpressCardBuilder()
                    .id(UUID.randomUUID())
                    .CVV("1234")
                    .FAN("2720999989955578")
                    .ownerName("Gustavo de Almeida")
                    .expiryDate(expiryDate)
                    .build();
        }

        throw new InvalidCreditCardTypeException(Errors.INVALID_CREDIT_CARD_TYPE);
    }
}
