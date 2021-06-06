package com.example.creditcardapi.stub;

import com.example.creditcardapi.domain.CardDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StubGenerator {

    public static Page<CardDetail> getCardDetailBEPage() {
        List<CardDetail> cardDetailList = new ArrayList<>();
        CardDetail cardDetail1 = CardDetail.builder()
                .id("id1")
                .customerName("Customer1")
                .cardNumber("1234567890")
                .cardLimit("£1000")
                .cardBalance("£1000")
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();

        CardDetail cardDetail2 = CardDetail.builder()
                .id("id2")
                .customerName("Customer2")
                .cardNumber("12345678901")
                .cardLimit("£1000")
                .cardBalance("£1000")
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();

        CardDetail cardDetail3 = CardDetail.builder()
                .id("id1")
                .customerName("Customer1")
                .cardNumber("1234567890")
                .cardLimit("£1000")
                .cardBalance("£1000")
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();

        CardDetail cardDetail4 = CardDetail.builder()
                .id("id2")
                .customerName("Customer2")
                .cardNumber("12345678901")
                .cardLimit("£1000")
                .cardBalance("£1000")
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();

        CardDetail cardDetail5 = CardDetail.builder()
                .id("id1")
                .customerName("Customer1")
                .cardNumber("1234567890")
                .cardLimit("£1000")
                .cardBalance("£1000")
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();

        CardDetail cardDetail6 = CardDetail.builder()
                .id("id2")
                .customerName("Customer2")
                .cardNumber("12345678901")
                .cardLimit("£1000")
                .cardBalance("£1000")
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();

        cardDetailList.add(cardDetail1); cardDetailList.add(cardDetail2); cardDetailList.add(cardDetail3); cardDetailList.add(cardDetail4);
        cardDetailList.add(cardDetail5); cardDetailList.add(cardDetail6);
        Page<CardDetail> cardDetailsBEList = new PageImpl<>(cardDetailList);
        return cardDetailsBEList;
    }

    public static Page<CardDetail> getCardDetailBEPageEmpty() {
        List<CardDetail> cardDetailList = new ArrayList<>();
        Page<CardDetail> cardDetailsBEList = new PageImpl<>(cardDetailList);
        return cardDetailsBEList;
    }

    public static CardDetail getCardDetailBE() {

        return CardDetail.builder()
                .id("1123")
                .customerName("Customer1")
                .cardNumber("1234567890")
                .cardLimit("£1000")
                .cardBalance("£1000")
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();
    }

    public static com.example.creditcardapi.model.CardDetail getCardModel() {
       return com.example.creditcardapi.model.CardDetail
                .builder()
                .customerName("Cust1").cardNumber("5555555555554444").limit("£737")
                .build();
    }
}
