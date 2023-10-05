package com.galmv_.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AddCreditCardDTO
        (
                @NotEmpty(message = "The CVV field cannot be empty!")
                @Size(min = 3, max = 4, message = "The CVV field must have the correct length!")
                String CVV,
                @NotEmpty(message = "The FAN field cannot be empty!")
                @Size(min = 15, max = 19, message = "The FAN field must have the correct length!")
                String FAN,
                @NotEmpty(message = "The Owner Name field cannot be empty!")
                String ownerName,
                @NotEmpty(message = "The Expiry Date field cannot be empty!")
                String expiryDate,
                @NotEmpty(message = "The Type field cannot be empty!")
                String type
        ){};
