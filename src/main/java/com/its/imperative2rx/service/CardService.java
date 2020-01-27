package com.its.imperative2rx.service;

import com.its.imperative2rx.entity.CardEntity;
import com.its.imperative2rx.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    /** i2r - Step 5.1*/
    private final WebClient webClient;

    public CardService(CardRepository cardRepository, WebClient.Builder webClientBuilder) {
        this.cardRepository = cardRepository;
        this.webClient = webClientBuilder
                            .baseUrl("http://localhost:7080")
                            .build();
    }

    // COMMENTED CODE
    /*private final RestTemplate restTemplate;

    public CardService(CardRepository cardRepository, RestTemplateBuilder builder) {
        this.cardRepository = cardRepository;
        this.restTemplate = builder.build();
    }*/
    /** i2r - Step 5.1*/

    public CardEntity getCardById(Long id) {
        log.info("Entering and leaving getCardById with parameter id - {}" , id);
        return cardRepository
                    .findById(id)
                    .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Card " + id + "Not found")
                    );
    }

    public List<CardEntity> getCards() {
        log.info("Entering and leaving getCards");
        return (List<CardEntity>) cardRepository.findAll();
    }

    /** i2r - Step 5.3*/

    public Mono<CardEntity> enroll(CardEntity aCard) {
        log.info("Entering enroll");
        return generateAlias(aCard.getCardNumber())
                    .flatMap(cardAlias -> {
                        aCard.setAlias(cardAlias);
                        return Mono.just(aCard);
                    })
                    .map(cardRepository :: save);



    }
    /*public CardEntity enroll(CardEntity aCard) {
        log.info("Entering enroll");
        String cardNo = aCard.getCardNumber();
        log.info("Generating card alias+++++");

        Mono<String> alias = generateAlias(cardNo);
        alias
            .subscribe(aliasValue -> aCard.setAlias(aliasValue))
            ;

        //COMMENTED CODE
        *//*String alias = generateAlias(cardNo);
        aCard.setAlias(alias);*//*


        log.info("Saving card in db");
        return cardRepository.save(aCard);
    }*/
    /** i2r - Step 5.3*/

    /** i2r - Step 5.2*/
    private Mono<String> generateAlias(String cardNo) {
        log.info("Generating alias for cardNo : {} by invoking downstream system ", cardNo);
        return webClient
                .get()
                .uri("/{cardNo}", cardNo)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(exception -> Mono.just("Card alias server is down. Plz try after some time"));


    }

    //COMMENTED CODE
    /*private String generateAlias(String cardNo) {
        log.info("Generating alias for cardNo : {} by invoking downstream system ", cardNo);
        return restTemplate
                .getForObject(String.format("http://localhost:7080/%s", cardNo), String.class);
    }*/
    /** i2r - Step 5.2*/

    public void deleteAll() {
        log.info("Entering and leaving deleteAll");
        cardRepository.deleteAll();
    }
}
