package com.customer.api.application.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class WishlistProductBodyRequest implements Serializable {

    @NotBlank
    @JsonProperty("product_id")
    private String productId;

}
