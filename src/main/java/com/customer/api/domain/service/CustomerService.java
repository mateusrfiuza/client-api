package com.customer.api.domain.service;

import com.customer.api.domain.Customer;
import com.customer.api.domain.repository.CustomerRepository;
import com.customer.api.domain.service.exception.CustomerAlreadyRegisteredException;
import com.customer.api.domain.service.exception.CustomerNotFoundException;
import com.customer.api.dataprovider.repository.exception.RegisterNotFoundException;
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
                .then(repository.save(customer));
    }

    public Mono<Customer> get(final UUID customerId) {
        return this.repository.findById(customerId)
                .onErrorMap(RegisterNotFoundException.class, e -> new CustomerNotFoundException());
    }

    public Mono<Void> update(final Customer newCustomer, final UUID customerId) {
        return this.get(customerId)
                    .flatMap(result -> {
                        result.setName(newCustomer.getName());
                        result.setEmail(newCustomer.getEmail());
                        return Mono.just(result);
                    }).flatMap(repository::save).then();
    }

    public Mono<Void> delete(final UUID customerId) {
        return this.repository.delete(customerId);
    }


}
