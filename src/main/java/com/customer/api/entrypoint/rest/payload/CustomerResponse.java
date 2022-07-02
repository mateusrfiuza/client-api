package com.customer.api.entrypoint.rest.payload;

import com.customer.api.domain.Customer;
import lombok.Data;

import java.util.UUID;

@Data
public class CustomerResponse {

    private UUID id;
    private String name;
    private String email;

    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
    }

}
