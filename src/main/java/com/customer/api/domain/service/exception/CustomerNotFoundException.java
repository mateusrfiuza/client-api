package com.customer.api.domain.service.exception;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(){
        super("Customer not found");
    }
}
