package com.client.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Client {

    private UUID id;
    private String email;
    private String name;
    private Wishlist wishlist;



}

