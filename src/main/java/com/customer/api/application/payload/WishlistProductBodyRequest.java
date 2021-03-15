package com.customer.api.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WishlistProductBodyRequest implements Serializable {

    @JsonProperty("product_id")
    private String productId;

}
