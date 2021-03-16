package com.customer.api.domain.service.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(){
        super("Product not found");
    }
}
