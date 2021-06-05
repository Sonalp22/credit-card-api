package com.example.creditcardapi.service;

import com.example.creditcardapi.domain.CardDetail;
import com.example.creditcardapi.mapper.CardDetailsMapper;
import com.example.creditcardapi.model.CardDetails;
import com.example.creditcardapi.model.CardDetailsWithPagination;
import com.example.creditcardapi.model.PaginationParams;
import com.example.creditcardapi.repository.CardDetailsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardDetailsServiceImpl implements CardDetailsService {

    private final CardDetailsRepository cardDetailsRepository;
    private final CardDetailsMapper cardDetailsMapper;

    @Override
    public CardDetailsWithPagination getCardDetails(String page, Integer limit) {

        CardDetails cardDetails;
        Page<CardDetail> cardDetailPage = cardDetailsRepository
                .findAll(PageRequest.of(Integer.valueOf(page)-1, limit));
        List<CardDetail> cardDetailData = cardDetailPage.getContent();

        if(!cardDetailData.isEmpty()) {
            cardDetails = cardDetailsMapper.mapTransportOrders(cardDetailData.stream());
        } else
            throw  new RuntimeException("No data found");
        PaginationParams pagination = getPagination(cardDetailPage, page);

        CardDetailsWithPagination cardDetailsWithPagination = new CardDetailsWithPagination();
        cardDetailsWithPagination.setCardDetails(cardDetails);
        cardDetailsWithPagination.setPaginationData(pagination);
        return cardDetailsWithPagination;
    }

    private PaginationParams getPagination(Page<CardDetail> cardDetailPage, String page) {
        PaginationParams pagination = new PaginationParams();
        pagination.setFirstPage("1");
        pagination.setCurrentPage(page);
        pagination.setNextPage(cardDetailPage.isLast() ? String.valueOf(Integer.parseInt(page)+1) : null);
        pagination.setLastPage(String.valueOf(cardDetailPage.getTotalPages()));
        return pagination;
    }


    @Override public String addCardDetails(com.example.creditcardapi.model.CardDetail chargeRequestData) {

        return null;
    }
}
