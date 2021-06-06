package com.example.creditcardapi.exceptions.handler;

import com.example.creditcardapi.exceptions.BadRequestException;
import com.example.creditcardapi.exceptions.DataNotFoundException;
import com.example.creditcardapi.model.ApiError;
import com.example.creditcardapi.model.ApiValidationError;
import com.example.creditcardapi.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomExceptionHandler class for Handling Custom exceptions
 */

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> customException(BadRequestException ex) {
        ApiError response=new ApiError();
        response.setMessage("BAD_REQUEST");
        response.setDebugMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiError> customException(DataNotFoundException ex) {
        ApiError response=new ApiError();
        response.setMessage(Constants.DATA_NOT_FOUND);
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> customException(MethodArgumentNotValidException ex, ServletWebRequest servletWebRequest) {
        List<ApiValidationError> subErrors = new ArrayList<>();
        ApiError response=new ApiError();
        response.setMessage("BAD_REQUEST");
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {

            ApiValidationError validationError = new ApiValidationError(fieldError.getField(),
                    fieldError.getRejectedValue().toString(), fieldError.getDefaultMessage());
            subErrors.add(validationError);
        }
        response.setSubErrors(subErrors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
