package com.its.imperative2rx.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "seq_card", initialValue = 1, allocationSize = 1)
public class CardEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_card")
    private Long id;
    private String issuingNetwork;
    private String cardNumber;
    private String name;
    private String expiryDate;
    private String alias;
}
