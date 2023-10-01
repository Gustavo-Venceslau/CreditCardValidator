package com.galmv_.domain.controllers;

import com.galmv_.domain.dtos.AddCreditCardDTO;
import com.galmv_.domain.entities.CreditCard;
import com.galmv_.domain.services.AddCreditCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payment/creditCard")
@RequiredArgsConstructor
@Slf4j
public class AddCreditCardController {

    private final AddCreditCardService service;

    @PostMapping
    public ResponseEntity<CreditCard> add(@RequestBody AddCreditCardDTO creditCard){
        try {
            CreditCard creditCardCreated = service.add(creditCard);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(creditCardCreated.getId()).toUri();

            return ResponseEntity.created(uri).body(creditCardCreated);
        }
        catch (RuntimeException e){
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
