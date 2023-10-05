package creditCardValidatition.creditCardTest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.exceptions.custom.*;
import com.galmv_.domain.services.AddCreditCardService;
import creditCardValidatition.config.IntegrationTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;


import static org.junit.jupiter.api.Assertions.*;

public class AddCreditCardControllerTest extends IntegrationTestConfig {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private AddCreditCardService service;

    @Test
    @DisplayName("should to able to add a new valid american express credit card in /payment/creditCard")
    public void TestSuccessAddAmericanExpressCreditCard() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO(
                        "1234",
                        "3440215794378998",
                    "Gustavo de Almeida",
                    "2024-08-01",
                        "AMERICAN_EXPRESS_CARD");

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
    @DisplayName("should to able to add a new valid common credit card in /payment/creditCard")
    public void TestSuccessAddCommonCreditCard() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO(
                        "123",
                        "2720999989955578",
                  "Gustavo de Almeida",
                   "2024-08-01",
                        "COMMON_CREDIT_CARD");



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
    @DisplayName("should not to able to add a new credit card in /payment/creditCard if this one already exists")
    public void TestFailAddAlreadyExistingCreditCard() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO(
                "1234",
                "3440215794378998",
                "Gustavo de Almeida",
                "2024-08-01",
                "AMERICAN_EXPRESS_CARD"
        );

        this.service.add(creditCardDTO);

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CreditCardAlreadyExistsException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to able to add a new credit card in /payment/creditCard if CVV is invalid")
    public void TestFailAddCreditCardWithWrongCVV() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO(
                        "123",
                        "3784533587163356",
                    "Gustavo de Almeida",
                    "2024-08-01",
                        "AMERICAN_EXPRESS_CARD"
        );

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCVVException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to able to add a new credit card in /payment/creditCard if FAN length is invalid")
    public void TestFailAddCreditCardWithWrongFANLength() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO(
                        "1234",
                        "37845335871633",
                  "Gustavo de Almeida",
                    "2024-08-01",
                        "AMERICAN_EXPRESS_CARD"
        );

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to able to add a new American Express credit card in /payment/creditCard if FAN number is invalid")
    public void TestFailAddAmericanExpressCreditCardWithWrongFANNumber() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO(
                "1234",
                "3784533587163355",
                "Gustavo de Almeida",
                "2024-08-01",
                "AMERICAN_EXPRESS_CARD"
        );

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCreditCardFANException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to able to add a new common credit card in /payment/creditCard if FAN number is invalid")
    public void TestFailAddCommonCreditCardWithWrongFANNumber() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO(
                "123",
                "2720999989955577",
                "Gustavo de Almeida",
                "2024-08-01",
                "COMMON_CREDIT_CARD"
        );

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCreditCardFANException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to able to add a new credit card in /payment/creditCard if expiry date is invalid")
    public void TestFailAddCreditCardWithWrongExpiryDate() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO(
                        "1234",
                        "3440215794378998",
                    "Gustavo de Almeida",
                    "2019-08-01",
                        "AMERICAN_EXPRESS_CARD");

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidExpiryDateException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("should not to able to add a new credit card in /payment/creditCard if expiry date number is invalid")
    public void TestFailAddCreditCardWithWrongExpiryDateNumber() throws Exception {

        AddCreditCardDTO creditCardDTO = new
                AddCreditCardDTO(
                "1234",
                "3784533587163356",
                "Gustavo de Almeida",
                "2019-00-01",
                "AMERICAN_EXPRESS_CARD");

        String creditCardDTOMapped = mapper.writeValueAsString(creditCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/creditCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(creditCardDTOMapped)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidExpiryDateNumberException))
                .andDo(MockMvcResultHandlers.print());
    }
}