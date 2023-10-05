package com.galmv_.domain.services;

import com.galmv_.domain.constants.Errors;
import com.galmv_.domain.entities.CreditCard;
import com.galmv_.domain.repositories.CreditCardRepository;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.exceptions.custom.CreditCardAlreadyExistsException;
import com.galmv_.domain.factories.CreditCardFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddCreditCardService {

    private final CreditCardRepository repository;
    private final CreditCardFactory factory;


    public CreditCard add(AddCreditCardDTO creditCard){
        Optional<CreditCard> optionalCreditCard = this.repository.findByFAN(creditCard.FAN());

        if(optionalCreditCard.isPresent()){
            throw new CreditCardAlreadyExistsException(Errors.CREDIT_CARD_ALREADY_EXITS);
        }

        CreditCard card = factory.getCreditCard(creditCard);

        return repository.save(card);
    }
}
