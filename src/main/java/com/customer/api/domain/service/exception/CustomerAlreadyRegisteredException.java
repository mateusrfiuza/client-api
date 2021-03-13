package com.customer.api.domain.service.exception;

public class CustomerAlreadyRegisteredException extends Exception {

    public CustomerAlreadyRegisteredException(){
        super("Customer already registered");
    }
}
