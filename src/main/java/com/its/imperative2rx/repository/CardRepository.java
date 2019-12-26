package com.its.imperative2rx.repository;

import com.its.imperative2rx.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
}
