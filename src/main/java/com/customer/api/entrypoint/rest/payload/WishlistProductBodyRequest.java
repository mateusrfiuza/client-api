package com.customer.api.entrypoint.rest.payload;

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
