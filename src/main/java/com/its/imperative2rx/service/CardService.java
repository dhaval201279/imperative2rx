package com.its.imperative2rx.service;

import com.its.imperative2rx.entity.CardEntity;
import com.its.imperative2rx.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    private final RestTemplate restTemplate;

    public CardService(CardRepository cardRepository, RestTemplateBuilder builder) {
        this.cardRepository = cardRepository;
        this.restTemplate = builder.build();
    }

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


    public CardEntity enroll(CardEntity aCard) {
        log.info("Entering enroll");
        String cardNo = aCard.getCardNumber();
        log.info("Generating card alias");
        String alias = generateAlias(cardNo);
        aCard.setAlias(alias);
        log.info("Saving card in db");
        return cardRepository.save(aCard);
    }

    private String generateAlias(String cardNo) {
        log.info("Generating alias for cardNo : {} by invoking downstream system ", cardNo);
        return restTemplate
                .getForObject(String.format("http://localhost:7080/%s", cardNo), String.class);
    }

    public void deleteAll() {
        log.info("Entering and leaving deleteAll");
        cardRepository.deleteAll();
    }
}
