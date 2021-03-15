package com.customer.api.infrastructure.repository.http;

import com.customer.api.domain.Product;
import com.customer.api.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductClient client;

    @Override
    public Mono<Product> findById(String id) {
        return null;
    }
}
