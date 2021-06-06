package com.example.creditcardapi.controller;

import com.example.creditcardapi.model.CardAdditionAcknowledgement;
import com.example.creditcardapi.model.CardDetail;
import com.example.creditcardapi.model.CardDetails;
import com.example.creditcardapi.model.CardDetailsWithPagination;
import com.example.creditcardapi.service.CardDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller class for storeCardDetails ang getCardDetails method
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class CardDetailsController {

    private final CardDetailsService service;


    /**
     * @param page page number
     * @param limit no of records
     * @return response entity of type ChargeRequests model
     */
    @CrossOrigin(exposedHeaders = "First-Page, Previous-Page, Current-Page, Next-Page, Last-Page")
    @GetMapping(path = "/card-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDetails> getCardDetails(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {

        log.info("Inside Controller method getChargeRequests()");
        CardDetailsWithPagination cardDetailsWithPagination = service.getCardDetails(page,limit);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("First-Page",cardDetailsWithPagination.getPaginationData().getFirstPage());
        responseHeaders.set("Previous-Page", cardDetailsWithPagination.getPaginationData().getPreviousPage());
        responseHeaders.set("Current-Page", cardDetailsWithPagination.getPaginationData().getCurrentPage());
        responseHeaders.set("Next-Page", cardDetailsWithPagination.getPaginationData().getNextPage());
        responseHeaders.set("Total-Records", cardDetailsWithPagination.getPaginationData().getTotalNoOfRecords());
        return ResponseEntity.ok().headers(responseHeaders).body(cardDetailsWithPagination.getCardDetails());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/card-detail/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public CardAdditionAcknowledgement storeCardDetails(@Valid @RequestBody CardDetail cardDetailData) {

        log.info("Inside Controller method addChargeRequest()");
         return service.addCardDetails(cardDetailData);
    }
}
