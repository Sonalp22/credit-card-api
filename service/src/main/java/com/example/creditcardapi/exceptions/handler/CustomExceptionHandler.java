package com.example.creditcardapi.exceptions.handler;

import com.example.creditcardapi.exceptions.BadRequestException;
import com.example.creditcardapi.exceptions.DataNotFoundException;
import com.example.creditcardapi.model.ApiError;
import com.example.creditcardapi.model.ApiValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

  /*  @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiError> dataNotFoundException(DataNotFoundException ex, ServerHttpRequest request) {
        log.info(String.format("%s - %s NOT_FOUND", request.getPath(), Objects.requireNonNull(request.getMethod()).name()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(Objects.requireNonNull(request.getMethod()).name(), request.getURI().getPath(), 404, LocalDateTime.now(),
                        ex.getLocalizedMessage(), ex.getLocalizedMessage(), null));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ApiError> handleCustomException(WebExchangeBindException webBindException, ServerHttpRequest request) {
        if(!webBindException.getGlobalErrors().isEmpty()){
            ObjectError globalError = webBindException.getGlobalError();
            String defaultMsg;
            if(null != globalError)
                defaultMsg = globalError.getDefaultMessage();
            else
                defaultMsg = null;
            return ResponseEntity.badRequest()
                    .body(new ApiError(Objects.requireNonNull(request.getMethod()).name(), request.getURI().getPath(), 400,
                            LocalDateTime.now(ZoneOffset.UTC), webBindException.getReason(), webBindException.getReason(),
                            Arrays.asList(new ApiValidationError("request body", null,
                                    defaultMsg))));
        }

        return ResponseEntity.badRequest()
                .body(new ApiError(Objects.requireNonNull(request.getMethod()).name(), request.getURI().getPath(), 400,
                        LocalDateTime.now(ZoneOffset.UTC), webBindException.getReason(), webBindException.getReason(),
                        this.getValidationErrors(webBindException)));
    }*/

    private List<ApiValidationError> getValidationErrors(WebExchangeBindException webExchangeBindException) {

        return webExchangeBindException.getFieldErrors()
                .stream()
                .map(er -> {
                    log.info("validation error on field: " + er.getField() + " value:" + er.getRejectedValue());
                    Object  rejectedVal = er.getRejectedValue() instanceof LocalDate ? ((LocalDate)er.getRejectedValue()).format(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd")): er.getRejectedValue();

                    return new ApiValidationError(er.getField(), Objects.requireNonNullElse(rejectedVal, "").toString(),
                            er.getDefaultMessage());
                })
                .collect(Collectors.toList());
    }

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
        response.setMessage("Data Not Found");
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
