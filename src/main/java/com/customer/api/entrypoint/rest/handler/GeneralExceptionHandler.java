package com.customer.api.entrypoint.rest.handler;

import com.customer.api.domain.service.exception.CustomerAlreadyRegisteredException;
import com.customer.api.domain.service.exception.CustomerNotFoundException;
import com.customer.api.domain.service.exception.ProductAlreadyRegisteredException;
import com.customer.api.domain.service.exception.ProductNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = {CustomerAlreadyRegisteredException.class, ProductNotFoundException.class, ProductAlreadyRegisteredException.class})
    protected ResponseEntity<ErrorResponse> handleBadRequest(final Exception ex) {
        final var apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleUnregisteredCustomer(final CustomerNotFoundException ex) {
        final var apiError = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(final WebExchangeBindException ex) {
        return new ResponseEntity<>(processFieldErrors(ex.getFieldErrors()), HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse processFieldErrors(final List<FieldError> fieldErrors) {
        final var error = new ErrorResponse(HttpStatus.BAD_REQUEST, "validation error");
        fieldErrors.forEach(fieldError ->
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage())
        );

        return error;
    }

}
