package com.example.creditcardapi.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;

/**
 * ApiError
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-05T12:37:12.571907500+01:00[Europe/London]")

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError   {
  @JsonProperty("method")
  private String method;

  @JsonProperty("requestUri")
  private String requestUri;

  @JsonProperty("status")
  private int status;

  @JsonProperty("timestamp")
  private LocalDateTime timestamp;

  @JsonProperty("message")
  private String message;

  @JsonProperty("debugMessage")
  private String debugMessage;

  @JsonProperty("subErrors")
  @Valid
  private List<ApiValidationError> subErrors = null;

  public ApiError method(String method) {
    this.method = method;
    return this;
  }


  public ApiError(String requestUri, HttpStatus status, Throwable ex) {
    this.requestUri = requestUri;
    this.status = status.value();
    this.message = "Validation Error";
    this.debugMessage = ex.getLocalizedMessage();
  }

}

