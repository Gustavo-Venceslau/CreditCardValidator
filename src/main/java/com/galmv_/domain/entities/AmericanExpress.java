package com.galmv_.domain.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class AmericanExpress extends CreditCard {

    @Builder(builderMethodName = "AmericanExpressCardBuilder")
    public AmericanExpress(UUID id, String FAN, String CVV, String ownerName, Calendar expiryDate) {
        super(id, FAN, CVV, ownerName, expiryDate);
    }
}
