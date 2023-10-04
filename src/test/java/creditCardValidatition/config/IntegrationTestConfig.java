package creditCardValidatition.config;

import com.galmv_.CreditCardApplication;
import com.galmv_.domain.CreditCardRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreditCardApplication.class
)
@AutoConfigureMockMvc
public class IntegrationTestConfig {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected CreditCardRepository repository;

    @AfterEach
    public void after(){
        this.repository.deleteAll();
    }
}
