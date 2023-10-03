package com.galmv_.domain.entities;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class AmericanExpress extends CreditCard {

    @Builder(builderMethodName = "AmericanExpressCardBuilder")
    public AmericanExpress(UUID id, String FAN, String CVV, String ownerName, LocalDate expiryDate) {
        super(id, FAN, CVV, ownerName, expiryDate);
    }
}
