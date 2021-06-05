package com.example.creditcardapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.Valid;

/**
 * ApiError
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-05T12:37:12.571907500+01:00[Europe/London]")

public class ApiError   {
  @JsonProperty("method")
  private String method;

  @JsonProperty("requestUri")
  private String requestUri;

  @JsonProperty("status")
  private String status;

  @JsonProperty("timestamp")
  private String timestamp;

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

}

