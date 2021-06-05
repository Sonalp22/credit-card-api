package com.example.creditcardapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

/**
 * CardDetails
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-05T12:37:12.571907500+01:00[Europe/London]")

public class CardDetails   {
  @JsonProperty("cardDetails")
  @Valid
  private List<CardDetail> cardDetails = null;

  public CardDetails cardDetails(List<CardDetail> cardDetails) {
    this.cardDetails = cardDetails;
    return this;
  }

  public CardDetails addCardDetailsItem(CardDetail cardDetailsItem) {
    if (this.cardDetails == null) {
      this.cardDetails = new ArrayList<>();
    }
    this.cardDetails.add(cardDetailsItem);
    return this;
  }
}

