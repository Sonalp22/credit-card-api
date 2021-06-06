package com.example.creditcardapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Domain class for Card Entity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CREDIT_CARD_DETAILS")
public class CardDetail {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID",columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;

    @Column(name = "CARD_NUMBER", nullable = false)
    private String cardNumber;

    @Column(name = "CARD_LIMIT")
    private String cardLimit;

    @Column(name = "CARD_BALANCE")
    private String cardBalance;

    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

    @Column(name = "UPDATED_DATE_TIME")
    private LocalDateTime updatedDateTime;
}
