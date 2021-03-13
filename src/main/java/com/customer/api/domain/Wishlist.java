package com.customer.api.domain;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class Wishlist {

    private final String id;
    private final String description;
    private Set<Product> items;
}
