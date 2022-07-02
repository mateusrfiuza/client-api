package com.customer.api.dataprovider.repository.http;

import com.customer.api.domain.Product;
import com.customer.api.domain.repository.ProductRepository;
import com.customer.api.dataprovider.repository.exception.RegisterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductClient client;

    @Override
    public Mono<Product> findById(String id) {
        return client.getProduct(id)
                .onErrorResume(throwable -> {
                    if (throwable instanceof RegisterNotFoundException) {
                        return Mono.empty();
                    } else {
                        return Mono.error(throwable);
                    }
                });
    }
}
