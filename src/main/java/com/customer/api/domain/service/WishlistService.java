package com.customer.api.domain.service;

import com.customer.api.domain.WishlistProduct;
import com.customer.api.domain.repository.ProductRepository;
import com.customer.api.domain.repository.WishlistProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistProductRepository wishlistProductRepository;
    private final ProductRepository productRepository;

    public Mono<WishlistProduct> addProduct(final UUID clientId, final String productId) {
        return wishlistProductRepository.save(clientId, productId);
    }

    public Flux<WishlistProduct> getItems(UUID clientId) {
        return wishlistProductRepository.findByCustomerId(clientId);
    }

    public Mono<Void> delete(UUID id) {
        return wishlistProductRepository.deleteById(id);
    }





}
