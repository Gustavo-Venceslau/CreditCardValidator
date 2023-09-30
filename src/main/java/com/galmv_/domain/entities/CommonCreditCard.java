package com.galmv_.domain.entities;

import com.galmv_.domain.CreditCard;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public class CommonCreditCard extends CreditCard {

    @Builder(builderMethodName = "CommonCreditBuilder")
    public CommonCreditCard(UUID id, String PAN, String CVV, String ownerName, Date expiryDate) {
        super(id, PAN, CVV, ownerName, expiryDate);
    }

    @Override
    public boolean validateCreditCard() {
        return false;
    }
}