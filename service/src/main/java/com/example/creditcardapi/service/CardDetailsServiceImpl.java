package com.example.creditcardapi.service;

import com.example.creditcardapi.domain.CardDetail;
import com.example.creditcardapi.exceptions.BadRequestException;
import com.example.creditcardapi.exceptions.DataNotFoundException;
import com.example.creditcardapi.mapper.CardDetailsMapper;
import com.example.creditcardapi.model.CardAdditionAcknowledgement;
import com.example.creditcardapi.model.CardDetails;
import com.example.creditcardapi.model.CardDetailsWithPagination;
import com.example.creditcardapi.model.PaginationParams;
import com.example.creditcardapi.repository.CardDetailsRepository;
import com.example.creditcardapi.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CardDetailsServiceImpl to hold Business logic for controller method calls
 */

@Service
@AllArgsConstructor
@Slf4j
public class CardDetailsServiceImpl implements CardDetailsService {

    private final CardDetailsRepository cardDetailsRepository;
    private final CardDetailsMapper cardDetailsMapper;

    /**
     * getCardDetails method to fetch list view of available card data along with pagination info
     * @param page
     * @param limit
     * @return
     */
    @Override
    public CardDetailsWithPagination getCardDetails(String page, Integer limit) {

        log.info("Inside service getCardDetails method");

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
        pagination.setNextPage(cardDetailPage.isLast() ? String.valueOf(Integer.parseInt(page)) : null);
        pagination.setTotalNoOfRecords(String.valueOf(cardDetailPage.getTotalElements()));
        return pagination;
    }

    /**
     * addCardDetails method to save new card details to database
     * @param cardDetailData
     */

    @Override
    public CardAdditionAcknowledgement addCardDetails(com.example.creditcardapi.model.CardDetail cardDetailData) {
       Boolean isValid = validate(cardDetailData.getCardNumber());
       log.info("Luhn validation of card number is "+isValid);
       if(isValid){
           CardDetail cardDetailBE = cardDetailsRepository.save(cardDetailsMapper.mapCardDataToBE(cardDetailData));
           return CardAdditionAcknowledgement.builder().cardCreationId(cardDetailBE.getId()).build();
       }
       else
           throw new BadRequestException(Constants.CARD_NUMBER_INVALID_MESSAGE);
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

        return calculatedSum.get() %10 == 0;
    }
}
