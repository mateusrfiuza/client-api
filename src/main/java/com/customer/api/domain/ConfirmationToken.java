package com.customer.api.domain;

public class ConfirmationToken {

    private String value;

    public ConfirmationToken(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

