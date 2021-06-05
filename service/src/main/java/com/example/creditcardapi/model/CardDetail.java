package com.example.creditcardapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * CardDetail
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-05T12:37:12.571907500+01:00[Europe/London]")

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDetail   {
  @JsonProperty("customerName")
  private String customerName;

  @JsonProperty("cardNumber")
  private String cardNumber;

  @JsonProperty("limit")
  private String limit;

  @JsonProperty("createdDateTime")
  private LocalDateTime createdDateTime;

  @JsonProperty("updatedDeateTime")
  private LocalDateTime updatedDeateTime;

}

