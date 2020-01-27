package com.its.imperative2rx.controller;

import com.its.imperative2rx.entity.CardEntity;
import com.its.imperative2rx.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class CardLifecycleController {
    private final CardService cardService;

    public CardLifecycleController(CardService cardService) {
        this.cardService = cardService;
    }

    /** i2r Step 3.1 */
    @GetMapping("/card/{id}")
    public Mono<CardEntity> byCardId(@PathVariable Long id) {
        log.info("## Entering and leaving CardLifecycleController : byCardId after fetching card for id - {} ", id);
        return Mono.just(cardService.getCardById(id));
    }

    // COMMENTED CODE
    /*@GetMapping("/card/{id}")
    public CardEntity byCardId(@PathVariable Long id) {
        log.info("## Entering and leaving CardLifecycleController : byCardId after fetching card for id - {} ", id);
        return cardService.getCardById(id);
    }*/
    /** i2r Step 3.1 */

    /** i2r Step 2.1 */
    @GetMapping("/cards")
    public Flux<CardEntity> all() {
        log.info("Entering and leaving CardLifecycleController : all after returning all the cards");
        return Flux
                .fromIterable(cardService.getCards());
    }

    // COMMENTED CODE
    /*@GetMapping("/cards")
    public List<CardEntity> all() {
        log.info("Entering and leaving CardLifecycleController : all after returning all the cards");
        return cardService.getCards();
    }*/
    /** i2r Step 2.1 */

    /** i2r Step 4.1 */
    @PostMapping("/card")
    public Mono<CardEntity> addCard(@RequestBody CardEntity aCard) {
        log.info("Entering and leaving CardLifecycleController : addCard after saving card with no. - {}  ",
                aCard.getCardNumber());
        return cardService.enroll(aCard);

    }

    // COMMENTED CODE
    /*@PostMapping("/card")
    public CardEntity addCard(@RequestBody CardEntity aCard) {
        log.info("Entering and leaving CardLifecycleController : addCard after saving card with no. - {}  ",
                aCard.getCardNumber());
        return cardService.enroll(aCard);

    }*/
    /** i2r Step 4.1 */

    @DeleteMapping("/cards")
    public void deleteAllCards() {
        log.info("Entering and leaving CardLifecycleController : deleteAllCards after deleting all the cards  ");
        cardService.deleteAll();
    }
}
