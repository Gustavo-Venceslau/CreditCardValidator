package creditCardValidatition.creditCardTest.services;

import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.entities.CreditCard;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.exceptions.custom.*;
import com.galmv_.domain.services.AddCreditCardService;
import creditCardValidatition.config.UnitTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.*;

public class AddCreditCardTest extends UnitTestConfig {

    @Autowired
    private AddCreditCardService service;

    @Test
    @DisplayName("should to be able to add a valid american express credit card")
    public void testSuccessAddAmericanExpressCreditCard(){


        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "3440215794378998", "Gustavo de Almeida", "2024-08-01", "AMERICAN_EXPRESS_CARD");

        CreditCard creditCard = service.add(creditCardDTO);

        assertThat(creditCard.getId()).isNotNull();
    }

    @Test
    @DisplayName("should to be able to add a valid common credit card")
    public void testSuccessAddCommonCreditCard(){


        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("123", "3440215794378998", "Gustavo de Almeida", "2024-08-01", "COMMON_CREDIT_CARD");

        CreditCard creditCard = service.add(creditCardDTO);

        assertThat(creditCard.getId()).isNotNull();
    }

    @Test
    @DisplayName("should not to be able to add credit card if this one already exists")
    public void testFailAddCreditCardIfAlreadyExists(){
        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("123", "5425233430109903", "Gustavo de Almeida", "2024-08-01", "COMMON_CREDIT_CARD");

        this.service.add(creditCardDTO);

        assertThatExceptionOfType(CreditCardAlreadyExistsException.class).isThrownBy(() ->
                service.add(creditCardDTO)).withMessage(Errors.CREDIT_CARD_ALREADY_EXITS);
    }

    @Test
    @DisplayName("should not to be able to add american credit card if this one have the wrong starts with")
    public void testFailAddAmericanCreditCardWithWrongStartsWith(){
        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "3640215794378997", "Gustavo de Almeida", "2024-08-01", "AMERICAN_EXPRESS_CARD");

        assertThatExceptionOfType(InvalidCreditCardFANException.class).isThrownBy(() ->
                service.add(creditCardDTO)).withMessage(Errors.INVALID_CREDIT_CARD_FAN_NUMBER);
    }

    @Test
    @DisplayName("should not to be able to add american credit card if this one have wrong FAN number")
    public void testFailAddAmericanExpressCreditCardWithWrongFANNumber(){
        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "3640215794378996", "Gustavo de Almeida", "2024-08-01", "AMERICAN_EXPRESS_CARD");

        assertThatExceptionOfType(InvalidCreditCardFANException.class).isThrownBy(() ->
                service.add(creditCardDTO)).withMessage(Errors.INVALID_CREDIT_CARD_FAN_NUMBER);
    }

    @Test
    @DisplayName("should not to be able to add common credit card if this one have wrong FAN number")
    public void testFailAddCommonCreditCardWithWrongFANNumber(){
        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("123", "5350044247779145", "Gustavo de Almeida", "2024-08-01", "COMMON_CREDIT_CARD");

        assertThatExceptionOfType(InvalidCreditCardFANException.class).isThrownBy(() ->
                service.add(creditCardDTO)).withMessage(Errors.INVALID_CREDIT_CARD_FAN_NUMBER);
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
    @DisplayName("should not to be able to add a credit card if this one have wrong expiry date")
    public void testFailAddCreditCardWithWrongExpiryDate(){

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "372180165626375", "Gustavo de Almeida", "2019-08-01", "AMERICAN_EXPRESS_CARD");

        assertThatExceptionOfType(InvalidExpiryDateException.class).isThrownBy(() ->
                service.add(creditCardDTO)).withMessage(Errors.INVALID_EXPIRY_DATE);
    }

    @Test
    @DisplayName("should not to be able to add a credit card if this one have wrong expiry date number")
    public void testFailAddCreditCardWithWrongExpiryDateNumber(){

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "3440215794378997", "Gustavo de Almeida", "2024-00-01", "AMERICAN_EXPRESS_CARD");

        assertThatExceptionOfType(InvalidExpiryDateNumberException.class).isThrownBy(() ->
                service.add(creditCardDTO)).withMessage(Errors.INVALID_EXPIRY_DATE_NUMBER);
    }
}
