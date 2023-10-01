package com.galmv_.domain.entities;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
public class CommonCreditCard extends CreditCard {

    @Builder(builderMethodName = "CommonCreditBuilder")
    public CommonCreditCard(UUID id, String FAN, String CVV, String ownerName, Calendar expiryDate) {
        super(id, FAN, CVV, ownerName, expiryDate);
    }
}
