package com.example.creditcardapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * CardDetail
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-05T12:37:12.571907500+01:00[Europe/London]")

public class CardDetail   {
  @JsonProperty("customerName")
  private String customerName;

  @JsonProperty("cardNumber")
  private String cardNumber;

  @JsonProperty("limit")
  private BigDecimal limit;

  public CardDetail customerName(String customerName) {
    this.customerName = customerName;
    return this;
  }
}

