package com.galmv_.domain;

import com.galmv_.domain.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {

    Optional<CreditCard> findByFAN(String FAN);
}
