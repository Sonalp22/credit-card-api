package com.example.creditcardapi.controller;

import com.example.creditcardapi.model.CardDetail;
import com.example.creditcardapi.model.CardDetails;
import com.example.creditcardapi.service.CardDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    @GetMapping(path = "/card-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDetails> getChargeRequests(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit
    ) {
        return ResponseEntity.ok(service.getCardDetails(page, limit).getCardDetails());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/card-detail/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addChargeRequest(@RequestBody @Valid CardDetail chargeRequestData) {
        return service.addCardDetails(chargeRequestData);
    }
}
