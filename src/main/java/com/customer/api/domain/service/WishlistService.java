package com.customer.api.domain.service;

import com.customer.api.domain.Product;
import com.customer.api.domain.WishlistProduct;
import com.customer.api.domain.repository.ProductRepository;
import com.customer.api.domain.repository.WishlistProductRepository;
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

    private final WishlistProductRepository wishlistProductRepository;
    private final ProductRepository productRepository;
    private final CustomerService customerService;

    public Mono<WishlistProduct> addProduct(final UUID customerId, final String productId) {
        return customerService.get(customerId)
                .then(checkProductExists(productId))
                .then(checkProductAlreadyRegistered(customerId, productId))
                .then(wishlistProductRepository.save(customerId, productId));
    }

    public Flux<WishlistProduct> getItems(final UUID clientId) {
        return wishlistProductRepository.findByCustomerId(clientId)
                .parallel()
                .flatMap(wishlistProduct -> getAdditionalInformation(wishlistProduct).zipWith(Mono.just(wishlistProduct))
                .filter(result -> Objects.nonNull(result.getT1().getId()))
                .flatMap(tuple -> addComplementaryInfo(tuple.getT2(), tuple.getT1())))
                .sequential();
    }

    public Mono<Void> delete(final UUID id) {
        return wishlistProductRepository.deleteById(id);
    }

    public Mono<Void> checkProductExists(final String productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(ProductNotFoundException::new))
                .then();
    }

    public Mono<Void> checkProductAlreadyRegistered(final UUID customerId, final String productId) {
        return wishlistProductRepository.findByCustomerIdAndProductId(customerId, productId)
                .flatMap(result -> Mono.error(ProductAlreadyRegisteredException::new));
    }

    public Mono<Product> getAdditionalInformation(final WishlistProduct wishlistProduct) {
        return productRepository.findById(wishlistProduct.getProductId())
                .switchIfEmpty(wishlistProductRepository.deleteById(wishlistProduct.getId()).thenReturn(new Product()));
    }

    public Mono<WishlistProduct> addComplementaryInfo(final WishlistProduct wishlistProduct, final Product product) {
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
