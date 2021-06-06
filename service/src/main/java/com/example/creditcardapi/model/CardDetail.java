package com.example.creditcardapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * CardDetail
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-05T12:37:12.571907500+01:00[Europe/London]")

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDetail   {
  @JsonProperty("customerName")
  private String customerName;

  @JsonProperty("cardNumber")
  @Pattern(regexp = "^[0-9]{16,19}$", message = "Card Number must be between 16 to 19 digits only")
  private String cardNumber;

  @JsonProperty("limit")
  private String limit;

  @JsonProperty("balance")
  private String balance;

  @JsonProperty("createdDateTime")
  private LocalDateTime createdDateTime;

  @JsonProperty("updatedDeateTime")
  private LocalDateTime updatedDeateTime;

}

