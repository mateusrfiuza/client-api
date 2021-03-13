package com.customer.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Customer {

    private UUID id;
    private String email;
    private String name;
    private Wishlist wishlist;



}

