package com.galmv_.domain.dtos;

import com.galmv_.domain.factories.CreditCardType;

import java.util.Calendar;
import java.util.Date;

public record AddCreditCardDTO(String CVV, String FAN, String ownerName, String expiryDate, CreditCardType type){};
