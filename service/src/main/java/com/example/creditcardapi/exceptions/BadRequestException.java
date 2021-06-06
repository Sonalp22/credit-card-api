package com.example.creditcardapi.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) { super(message); }
}
