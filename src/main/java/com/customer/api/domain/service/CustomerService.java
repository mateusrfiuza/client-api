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
        return checkCustomerExists(customer)
                .then(this.repository.save(customer));
    }

    public Mono<Customer> get(final UUID id) {
        return this.repository.get(id)
                .onErrorResume(throwable -> {
                    if (throwable instanceof RegisterNotFoundException) {
                        return Mono.error(CustomerNotFoundException::new);
                    } else {
                        return Mono.error(throwable);
                    }
                });
    }

    public Mono<Void> delete(final UUID id) {
        return this.repository.delete(id);
    }

    private Mono<Void> checkCustomerExists(Customer customer) {
        final var exists = this.repository.existsByEmail(customer.getEmail());
        return exists.flatMap(aBoolean -> {
            if (aBoolean) {
                return Mono.error(CustomerAlreadyRegisteredException::new);
            }
            return null;
        });
    }

}
