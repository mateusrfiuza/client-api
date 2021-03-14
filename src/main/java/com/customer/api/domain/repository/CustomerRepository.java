package com.customer.api.domain.repository;

import com.customer.api.domain.Customer;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CustomerRepository {

    Mono<Customer> save(Customer customer);

    Mono<Customer> findById(UUID id);

    Mono<Customer> findByEmail(String email);

    Mono<Void> delete(UUID id);





}
