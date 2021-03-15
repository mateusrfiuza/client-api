package com.customer.api.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class WishlistProduct {

    private UUID id;
    private UUID customerId;
    private String title;
    private String brand;
    private String image;
    private Double price;
    private Double review;
    private String productId;
}
