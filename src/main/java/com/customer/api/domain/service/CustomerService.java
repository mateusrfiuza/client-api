package com.customer.api.domain.service;

import com.customer.api.domain.Customer;
import com.customer.api.domain.repository.CustomerRepository;
import com.customer.api.domain.service.exception.CustomerAlreadyRegisteredException;
import com.customer.api.domain.service.exception.CustomerNotFoundException;
import com.customer.api.infrastructure.repository.exception.RegisterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Mono<Customer> create(final Customer customer) {
        return repository.findByEmail(customer.getEmail())
                .flatMap(result -> Mono.error(CustomerAlreadyRegisteredException::new))
                .switchIfEmpty(repository.save(customer))
                .cast(Customer.class);
    }

    public Mono<Customer> get(final UUID id) {
        return this.repository.findById(id)
                .onErrorResume(throwable -> {
                    if (throwable instanceof RegisterNotFoundException) {
                        return Mono.error(CustomerNotFoundException::new);
                    } else {
                        return Mono.error(throwable);
                    }
                });
    }

    public Mono<Void> update(final Customer customer, final UUID id) {
        return this.get(id)
                    .flatMap(result -> {
                        result.setName(customer.getName());
                        result.setEmail(customer.getEmail());
                        return Mono.just(result);
                    }).flatMap(repository::save).then();
    }

    public Mono<Void> delete(final UUID id) {
        return this.repository.delete(id);
    }


}
