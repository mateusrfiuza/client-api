package com.customer.api.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class Product {

    private UUID id;
    private String title;
    private String brand;
    private String url;
    private Double price;
    private Double reviewScore;
}
