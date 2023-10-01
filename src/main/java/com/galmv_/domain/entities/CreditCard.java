package com.galmv_.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "creditCard")
@AllArgsConstructor
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
    private Calendar expiryDate;
}
