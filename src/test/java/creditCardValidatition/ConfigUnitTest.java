package creditCardValidatition;

import com.galmv_.CreditCardApplication;
import com.galmv_.domain.CreditCard;
import creditCardValidatition.factories.CreditCardFactory;
import creditCardValidatition.factories.CreditCardType;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreditCardApplication.class
)
public class ConfigUnitTest {
    protected CreditCard americanExpressCard = CreditCardFactory.getCreditCard(CreditCardType.AMERICAN_EXPRESS_CARD);
    protected CreditCard commonCreditCard = CreditCardFactory.getCreditCard(CreditCardType.COMMON_CREDIT_CARD);
}
