package creditCardValidatition.creditCardTest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import creditCardValidatition.config.IntegrationTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AddCreditCardControllerTest extends IntegrationTestConfig {

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("should to able to add a new valid credit card in /payment/creditCard")
    public void TestSuccessAddCreditCard() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "3784533587163356", "Gustavo de Almeida", "08/24", "AMERICAN_EXPRESS_CARD");

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(creditCardDTOMapped)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fan").value(creditCardDTO.FAN()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to able to add a new credit card in /payment/creditCard if CVV is invalid")
    public void TestFailAddCreditCardWithWrongCVV() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("123", "3784533587163356", "Gustavo de Almeida", "08/24", "AMERICAN_EXPRESS_CARD");

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to able to add a new credit card in /payment/creditCard if FAN length is invalid")
    public void TestFailAddCreditCardWithWrongFANLength() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "378453358716335", "Gustavo de Almeida", "08/24", "AMERICAN_EXPRESS_CARD");

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to able to add a new credit card in /payment/creditCard if FAN length is invalid")
    public void TestFailAddCreditCardWithWrongExpiryDate() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO("1234", "3784533587163356", "Gustavo de Almeida", "08/19", "AMERICAN_EXPRESS_CARD");

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
