package com.customer.api.domain;

import lombok.Data;

@Data
public class Product {

    private String id;
    private String title;
    private String brand;
    private String image;
    private Double price;
    private Double review;


}
