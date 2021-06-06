package com.example.creditcardapi.mapper;

import com.example.creditcardapi.domain.CardDetail;
import com.example.creditcardapi.model.CardDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Mapper class to map domain to model classes
 */

@Component
public class CardDetailsMapper {
    public CardDetails mapCardDetails(Stream<CardDetail> cardDetailsData) {
        Stream<com.example.creditcardapi.model.CardDetail> cardDetailStream = cardDetailsData.map(this::mapCardDetailData);
        List<com.example.creditcardapi.model.CardDetail> cardDetailSummary = cardDetailStream.collect(Collectors.toList());
        return CardDetails.builder()
                .cardDetails(cardDetailSummary)
                .build();
    }

    private com.example.creditcardapi.model.CardDetail mapCardDetailData(CardDetail cardDetailData) {
        com.example.creditcardapi.model.CardDetail cardDetailDataModel;
        cardDetailDataModel = com.example.creditcardapi.model.CardDetail.builder()
                .cardNumber(cardDetailData.getCardNumber())
                .customerName(cardDetailData.getCustomerName())
                .limit(cardDetailData.getCardLimit())
                .build();
        return cardDetailDataModel;
    }

    public CardDetail mapCardDataToBE(com.example.creditcardapi.model.CardDetail cardDetailData) {
        return CardDetail.builder()
                .customerName(cardDetailData.getCustomerName())
                .cardNumber(cardDetailData.getCardNumber())
                .cardLimit(StringUtils.isEmpty(cardDetailData.getLimit())?"£1500": cardDetailData.getLimit())
                .createdDateTime(LocalDateTime.now())
                .updatedDateTime(LocalDateTime.now())
                .build();
    }
}
