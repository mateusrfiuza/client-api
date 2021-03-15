package com.customer.api.domain.repository;

import com.customer.api.domain.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<Product> findById(String id);


}
