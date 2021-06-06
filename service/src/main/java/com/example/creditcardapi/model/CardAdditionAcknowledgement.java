package com.example.creditcardapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
            /**
 * CardAdditionAcknowledgement
 */

public class CardAdditionAcknowledgement {

  @JsonProperty("cardCreationId")
  private String cardCreationId;


}
