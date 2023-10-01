package creditCardValidatition.creditCardTest.utils;

import com.galmv_.domain.utils.ConvertExpiryDateToCalendar;
import creditCardValidatition.ConfigUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class ConvertExpiryDateToCalendarTest extends ConfigUnitTest {

    @Test
    @DisplayName("should to be able to convert a string expiry date to a Calendar object expiry date")
    public void testSuccessConvertExpiryDate(){
        String expiryDate = "08/29";

        Calendar convertedExpiredDate = ConvertExpiryDateToCalendar.convert(expiryDate);

        assertThat(convertedExpiredDate.get(Calendar.MONTH)).isEqualTo(Calendar.AUGUST);
    }
}
