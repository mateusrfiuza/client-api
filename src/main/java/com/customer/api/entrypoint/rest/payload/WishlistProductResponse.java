package com.customer.api.entrypoint.rest.payload;

import com.customer.api.domain.WishlistProduct;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
public class WishlistProductResponse implements Serializable {

    private UUID id;
    @JsonProperty("product_id")
    private String productId;
    private String title;
    private String image;
    private Double price;
    @JsonInclude(NON_NULL)
    private Double review;

    public WishlistProductResponse(WishlistProduct wishlistProduct) {
        this.id = wishlistProduct.getId();
        this.productId = wishlistProduct.getProductId();
        this.title = wishlistProduct.getTitle();
        this.image = wishlistProduct.getImage();
        this.price = wishlistProduct.getPrice();
        this.review = wishlistProduct.getReview();
    }

}
