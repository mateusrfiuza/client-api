package com.customer.api.domain.service;

import com.customer.api.domain.WishlistProduct;
import com.customer.api.domain.repository.ProductRepository;
import com.customer.api.domain.repository.WishlistProductRepository;
import com.customer.api.domain.service.exception.CustomerNotFoundException;
import com.customer.api.domain.service.exception.ProductAlreadyRegisteredException;
import com.customer.api.domain.service.exception.ProductNotFoundException;
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
    private final CustomerService customerService;

    public Mono<WishlistProduct> addProduct(final UUID customerId, final String productId) {
        return customerService.get(customerId)
                .then(checkProductExists(productId))
                .then(checkProductAlreadyRegistered(customerId, productId))
                .then(wishlistProductRepository.save(customerId, productId));
    }

    public Flux<WishlistProduct> getItems(final UUID clientId) {
        return wishlistProductRepository.findByCustomerId(clientId);
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


}
