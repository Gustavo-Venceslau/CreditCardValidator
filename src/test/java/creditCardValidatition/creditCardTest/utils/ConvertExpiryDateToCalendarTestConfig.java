package creditCardValidatition.creditCardTest.utils;

import com.galmv_.domain.utils.ConvertExpiryDateToLocalDate;
import creditCardValidatition.config.UnitTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

public class ConvertExpiryDateToCalendarTestConfig extends UnitTestConfig {

    @Test
    @DisplayName("should to be able to convert a string expiry date to a Calendar object expiry date")
    public void testSuccessConvertExpiryDate(){
        String expiryDate = "2029-08-01";

        LocalDate convertedExpiredDate = ConvertExpiryDateToLocalDate.convert(expiryDate);

        assertThat(convertedExpiredDate.getMonth()).isEqualTo(Month.AUGUST);
        assertThat(convertedExpiredDate.getYear()).isEqualTo(2029);
    }
}
