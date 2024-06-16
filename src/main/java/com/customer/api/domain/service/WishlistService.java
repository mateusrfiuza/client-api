package com.customer.api.domain.service;

import com.customer.api.domain.Product;
import com.customer.api.domain.WishlistProduct;
import com.customer.api.domain.repository.ProductRepository;
import com.customer.api.domain.repository.WishlistRepository;
import com.customer.api.domain.service.exception.ProductAlreadyRegisteredException;
import com.customer.api.domain.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final CustomerService customerService;

    public Mono<WishlistProduct> addProduct(final UUID customerId, final String productId) {
        return customerService.get(customerId)
                .then(validateProductExists(productId))
                .then(validateProductAlreadyRegistered(customerId, productId))
                .then(wishlistRepository.save(customerId, productId));
    }

    // TODO - NEXT UPGRADE, ADD REDIS TO RETURN WISHLIST
    public Flux<WishlistProduct> getItems(final UUID clientId) {
        return wishlistRepository.findByCustomerId(clientId)
                .parallel()
                .flatMap(wishlistProduct -> getProductAdditionalInformation(wishlistProduct).zipWith(Mono.just(wishlistProduct))
                .filter(result -> Objects.nonNull(result.getT1().getId()))
                .flatMap(tuple -> addComplementaryInfo(tuple.getT2(), tuple.getT1())))
                .sequential();
    }

    public Mono<Void> delete(final UUID id) {
        return wishlistRepository.deleteById(id);
    }

    public Mono<Void> validateProductExists(final String productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(ProductNotFoundException::new))
                .then();
    }

    public Mono<Void> validateProductAlreadyRegistered(final UUID customerId, final String productId) {
        return wishlistRepository.findByCustomerIdAndProductId(customerId, productId)
                .flatMap(result -> Mono.error(ProductAlreadyRegisteredException::new))
                .then();
    }

    private Mono<Product> getProductAdditionalInformation(final WishlistProduct wishlistProduct) {
        return productRepository.findById(wishlistProduct.getProductId())
                .switchIfEmpty(wishlistRepository.deleteById(wishlistProduct.getId()).thenReturn(new Product()));
    }

    private Mono<WishlistProduct> addComplementaryInfo(final WishlistProduct wishlistProduct, final Product product) {
        return Mono.fromCallable(() -> {
                wishlistProduct.setProductId(product.getId());
                wishlistProduct.setPrice(product.getPrice());
                wishlistProduct.setBrand(product.getBrand());
                wishlistProduct.setReview(product.getReview());
                wishlistProduct.setImage(product.getImage());
                wishlistProduct.setTitle(product.getTitle());
                return wishlistProduct;
        });
    }


}
