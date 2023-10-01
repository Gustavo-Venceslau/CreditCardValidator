package com.galmv_.domain.entities;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
public class AmericanExpress extends CreditCard {

    @Builder(builderMethodName = "AmericanExpressCardBuilder")
    public AmericanExpress(UUID id, String PAN, String CVV, String ownerName, Calendar expiryDate) {
        super(id, PAN, CVV, ownerName, expiryDate);
    }
}
