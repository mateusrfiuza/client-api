package com.customer.api.domain.service;

import com.customer.api.domain.WishlistProduct;
import com.customer.api.domain.repository.ProductRepository;
import com.customer.api.domain.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    //private final ProductRepository productRepository;

    public Mono<WishlistProduct> create(final UUID clientId, final String productId) {
        return wishlistRepository.save(clientId, productId);
    }

    public Flux<WishlistProduct> getItems(UUID clientId) {
        return wishlistRepository.findByCustomerId(clientId);
    }





}
