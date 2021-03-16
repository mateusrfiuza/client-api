package com.customer.api.domain.service.exception;

public class ProductAlreadyRegisteredException extends Exception {

    public ProductAlreadyRegisteredException(){
        super("Product already registered");
    }
}
