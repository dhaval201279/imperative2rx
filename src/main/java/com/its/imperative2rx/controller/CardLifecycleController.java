package com.its.imperative2rx.controller;

import com.its.imperative2rx.entity.CardEntity;
import com.its.imperative2rx.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class CardLifecycleController {
    private final CardService cardService;

    public CardLifecycleController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card/{id}")
    public CardEntity byCardId(@PathVariable Long id) {
        log.info("## Entering and leaving CardLifecycleController : byCardId after fetching card for id - {} ", id);
        return cardService.getCardById(id);
    }

    @GetMapping("/cards")
    public List<CardEntity> all() {
        log.info("Entering and leaving CardLifecycleController : all after returning all the cards");
        return cardService.getCards();
    }

    @PostMapping("/card")
    public CardEntity addCard(@RequestBody CardEntity aCard) {
        log.info("Entering and leaving CardLifecycleController : addCard after saving card with no. - {}  ",
                aCard.getCardNumber());
        return cardService.enroll(aCard);

    }

    @DeleteMapping("/cards")
    public void deleteAllCards() {
        log.info("Entering and leaving CardLifecycleController : deleteAllCards after deleting all the cards  ");
        cardService.deleteAll();
    }
}
