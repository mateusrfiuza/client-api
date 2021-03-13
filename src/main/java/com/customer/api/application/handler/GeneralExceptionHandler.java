package com.customer.api.application.handler;

import com.customer.api.domain.service.exception.CustomerAlreadyRegisteredException;
import com.customer.api.domain.service.exception.CustomerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = CustomerAlreadyRegisteredException.class)
    protected ResponseEntity handleCustomerAlreadyRegistered(final CustomerAlreadyRegisteredException ex) {
        final var apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    protected ResponseEntity handleUnregisteredCustomer(final CustomerNotFoundException ex) {
        final var apiError = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
