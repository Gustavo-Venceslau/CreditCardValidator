package creditCardValidatition.config;

import com.galmv_.CreditCardApplication;
import com.galmv_.domain.entities.CreditCard;
import creditCardValidatition.factories.CreditCardFactory;
import com.galmv_.domain.factories.CreditCardType;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreditCardApplication.class
)
public class UnitTestConfig {

    protected CreditCard americanExpressCard = CreditCardFactory.getCreditCard(CreditCardType.AMERICAN_EXPRESS_CARD);
    protected CreditCard commonCreditCard = CreditCardFactory.getCreditCard(CreditCardType.COMMON_CREDIT_CARD);
}
