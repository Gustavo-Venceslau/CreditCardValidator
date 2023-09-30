package com.galmv_.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "credit card")
@AllArgsConstructor
@Data
public abstract class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String PAN;
    @Column(nullable = false)
    private String CVV;
    @Column(nullable = false)
    private String ownerName;
    @Column(nullable = false)
    private Date expiryDate;

    public abstract boolean validateCreditCard();
}
