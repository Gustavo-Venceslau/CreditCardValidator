package creditCardValidatition.factories;

import com.galmv_.domain.CreditCard;
import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.entities.AmericanExpress;
import com.galmv_.domain.entities.CommonCreditCard;
import com.galmv_.domain.exceptions.InvalidCreditCardTypeException;

import java.util.Date;
import java.util.UUID;

public class CreditCardFactory {

    public static CreditCard getCreditCard(CreditCardType type){
        if(type == CreditCardType.COMMON_CREDIT_CARD){
            return CommonCreditCard.CommonCreditBuilder()
                    .id(UUID.randomUUID())
                    .CVV("123")
                    .PAN("5425233430109903")
                    .ownerName("Gustavo de Almeida")
                    .expiryDate(new Date())
                    .build();
        }
        else  if(type == CreditCardType.AMERICAN_EXPRESS_CARD){
            return AmericanExpress.AmericanExpressCardBuilder()
                    .id(UUID.randomUUID())
                    .CVV("123")
                    .PAN("5425233430109903")
                    .ownerName("Gustavo de Almeida")
                    .expiryDate(new Date())
                    .build();
        }

        throw new InvalidCreditCardTypeException(Errors.INVALID_CREDIT_CARD_TYPE);
    }
}
