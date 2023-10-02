package creditCardValidatition.config;

import com.galmv_.CreditCardApplication;
import com.galmv_.domain.CreditCardRepository;
import com.galmv_.domain.entities.CreditCard;
import creditCardValidatition.factories.CreditCardFactory;
import com.galmv_.domain.factories.CreditCardType;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreditCardApplication.class
)
public class UnitTestConfig {

    @Autowired
    protected CreditCardRepository repository;

    protected CreditCard americanExpressCard = CreditCardFactory.getCreditCard(CreditCardType.AMERICAN_EXPRESS_CARD);
    protected CreditCard commonCreditCard = CreditCardFactory.getCreditCard(CreditCardType.COMMON_CREDIT_CARD);

    @AfterEach
    public void after(){
        this.repository.deleteAll();
    }
}
