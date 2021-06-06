package com.example.creditcardapi.service;

import com.example.creditcardapi.domain.CardDetail;
import com.example.creditcardapi.exceptions.BadRequestException;
import com.example.creditcardapi.exceptions.DataNotFoundException;
import com.example.creditcardapi.mapper.CardDetailsMapper;
import com.example.creditcardapi.model.CardDetails;
import com.example.creditcardapi.model.CardDetailsWithPagination;
import com.example.creditcardapi.model.PaginationParams;
import com.example.creditcardapi.repository.CardDetailsRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
            cardDetails = cardDetailsMapper.mapCardDetails(cardDetailData.stream());
        } else
            throw  new DataNotFoundException("No data found");
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


    @Override
    public void addCardDetails(com.example.creditcardapi.model.CardDetail cardDetailData) {
       Boolean isValid = validate(cardDetailData.getCardNumber());
       if(isValid)
         cardDetailsRepository.save(cardDetailsMapper.mapCardDataToBE(cardDetailData));
       else
           throw new BadRequestException("Bad Request Invalid Card No.");
    }

    private Boolean validate(String cardNumber) {
        String reverseCardNo = StringUtils.reverse(cardNumber);
        AtomicInteger calculatedSum = new AtomicInteger();
        AtomicInteger i = new AtomicInteger();

        reverseCardNo.chars() //IntStream
                .mapToObj(x -> Integer.parseInt(String.valueOf( (char)x)))
                .forEach(currNo ->{
                    if(i.get() %2 == 0)
                        calculatedSum.addAndGet(currNo);
                    else {
                        int digitDouble = currNo*2;
                        calculatedSum.addAndGet(digitDouble > 9 ? digitDouble - 9 : digitDouble);
                    }
                    i.getAndIncrement();
                });

        return calculatedSum.get() %10 == 0 ?true : false;
    }
}
