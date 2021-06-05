package com.example.creditcardapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * ApiValidationError
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-05T12:37:12.571907500+01:00[Europe/London]")

public class ApiValidationError   {
  @JsonProperty("field")
  private String field;

  @JsonProperty("rejectedValue")
  private String rejectedValue;

  @JsonProperty("message")
  private String message;

  public ApiValidationError field(String field) {
    this.field = field;
    return this;
  }
}

