package com.customer.api.domain.repository;

import com.customer.api.domain.Customer;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CustomerRepository {

    Mono<Customer> save(Customer customer);

    Mono<Customer> get(UUID id);

    Mono<Boolean> existsByEmail(String email);

    Mono<Void> delete(UUID id);





}
