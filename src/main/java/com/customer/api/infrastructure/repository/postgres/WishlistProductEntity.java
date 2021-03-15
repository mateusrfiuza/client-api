package com.customer.api.infrastructure.repository.postgres;

import com.customer.api.domain.WishlistProduct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table(value = "challenge.wishlist_product")
@Data
@NoArgsConstructor
public class WishlistProductEntity implements Serializable {

    @Id
    @Column("id")
    private UUID id;

    @Column("customer_id")
    private UUID customerId;

    @Column("product_id")
    private String productId;

    public WishlistProductEntity(UUID customerId, String productId) {
        this.customerId = customerId;
        this.productId = productId;
    }


    public WishlistProduct toDomain() {
        final var wishlistProduct = new WishlistProduct();

        wishlistProduct.setId(this.id);
        wishlistProduct.setCustomerId(this.customerId);
        wishlistProduct.setProductId(this.productId);

        return wishlistProduct;
    }


}