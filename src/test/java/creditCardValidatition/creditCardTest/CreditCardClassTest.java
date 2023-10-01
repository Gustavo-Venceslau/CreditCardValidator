package creditCardValidatition.creditCardTest;

import creditCardValidatition.config.UnitTestConfig;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CreditCardClassTest extends UnitTestConfig {

    @Test
    @DisplayName("should to be able to instantiate a new american express credit card object")
    public void testSuccessInstantiateAmericanExpressCard(){

        assertThat(americanExpressCard.getId()).isNotNull();
        assertThat(americanExpressCard.getCVV()).isNotNull();
        assertThat(americanExpressCard.getFAN()).isNotNull();
        assertThat(americanExpressCard.getOwnerName()).isNotNull();
        assertThat(americanExpressCard.getExpiryDate()).isNotNull();
    }

    @Test
    @DisplayName("should to be able to instantiate a new common credit card object")
    public void testSuccessInstantiateCommonCreditCard(){

        assertThat(commonCreditCard.getId()).isNotNull();
        assertThat(commonCreditCard.getCVV()).isNotNull();
        assertThat(commonCreditCard.getFAN()).isNotNull();
        assertThat(commonCreditCard.getOwnerName()).isNotNull();
        assertThat(commonCreditCard.getExpiryDate()).isNotNull();
    }
}
