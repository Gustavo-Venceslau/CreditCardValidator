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
public class CommonCreditCard extends CreditCard {

    @Builder(builderMethodName = "CommonCreditBuilder")
    public CommonCreditCard(UUID id, String FAN, String CVV, String ownerName, LocalDate expiryDate) {
        super(id, FAN, CVV, ownerName, expiryDate);
    }
}
