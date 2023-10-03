package creditCardValidatition.creditCardTest.services;

import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.entities.CreditCard;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.exceptions.custom.InvalidCVVException;
import com.galmv_.domain.exceptions.custom.InvalidCreditCardFANException;
import com.galmv_.domain.exceptions.custom.InvalidExpiryDateException;
import com.galmv_.domain.services.AddCreditCardService;
import creditCardValidatition.config.UnitTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

public class AddCreditCardTest extends UnitTestConfig {

    @Autowired
    private AddCreditCardService service;

    @Test
    @DisplayName("should to be able to add a valid credit card")
    public void testSuccessAddCreditCard(){


        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "3440215794378998", "Gustavo de Almeida", "2024-08-01", "AMERICAN_EXPRESS_CARD");

        CreditCard creditCard = service.add(creditCardDTO);

        assertThat(creditCard.getId()).isNotNull();
    }

    @Test
    @DisplayName("should not to be able to add a credit card if CVV length is invalid")
    public void testFailAddCreditCardWithWrongCVV(){

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("123", "3440215794378997", "Gustavo de Almeida", "2024-08-01", "AMERICAN_EXPRESS_CARD");

        assertThatExceptionOfType(InvalidCVVException.class).isThrownBy(() ->
                service.add(creditCardDTO)).withMessage(Errors.INVALID_CVV_LENGTH);
    }

    @Test
    @DisplayName("should not to be able to add a credit card if PAN length is invalid")
    public void testFailAddCreditCardWithWrongFANLength(){

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "344021579437899", "Gustavo de Almeida", "2024-08-01", "AMERICAN_EXPRESS_CARD");

        assertThatExceptionOfType(InvalidCreditCardFANException.class).isThrownBy(() ->
                service.add(creditCardDTO)).withMessage(Errors.INVALID_CREDIT_CARD_FAN_LENGTH);
    }

    @Test
    @DisplayName("should not to be able to add a credit card if PAN length is invalid")
    public void testFailAddCreditCardWithWrongExpiryDate(){

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "3440215794378996", "Gustavo de Almeida", "2019-08-01", "AMERICAN_EXPRESS_CARD");

        assertThatExceptionOfType(InvalidExpiryDateException.class).isThrownBy(() ->
                service.add(creditCardDTO)).withMessage(Errors.INVALID_EXPIRY_DATE);
    }
}
