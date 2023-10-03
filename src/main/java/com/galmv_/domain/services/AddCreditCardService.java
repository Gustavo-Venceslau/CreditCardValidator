package com.galmv_.domain.services;

import com.galmv_.domain.entities.CreditCard;
import com.galmv_.domain.CreditCardRepository;
import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.factories.CreditCardFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCreditCardService {

    private final CreditCardRepository repository;
    private final CreditCardFactory factory;


    public CreditCard add(AddCreditCardDTO creditCard){
        CreditCard card = factory.getCreditCard(creditCard);

        return repository.save(card);
    }
}
