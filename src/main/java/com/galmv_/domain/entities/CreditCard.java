package com.galmv_.domain.entities;

import com.galmv_.domain.dtos.AddCreditCardDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "creditCard")
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String FAN;
    @Column(nullable = false)
    private String CVV;
    @Column(nullable = false)
    private String ownerName;
    @Column(nullable = false)
    private LocalDate expiryDate;

    public abstract boolean validateCreditCard(AddCreditCardDTO creditCard);
}
