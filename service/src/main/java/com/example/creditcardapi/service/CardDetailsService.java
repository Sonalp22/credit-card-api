package com.example.creditcardapi.service;

import com.example.creditcardapi.model.CardDetail;
import com.example.creditcardapi.model.CardDetails;
import com.example.creditcardapi.model.CardDetailsWithPagination;

public interface CardDetailsService {
    CardDetailsWithPagination getCardDetails(String page, Integer limit);

    String addCardDetails(CardDetail chargeRequestData);
}
