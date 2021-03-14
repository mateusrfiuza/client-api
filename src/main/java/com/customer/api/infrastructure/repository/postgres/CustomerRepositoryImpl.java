package com.customer.api.infrastructure.repository.postgres;

import com.customer.api.domain.Customer;
import com.customer.api.domain.repository.CustomerRepository;
import com.customer.api.infrastructure.repository.exception.RegisterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerDAO repository;

    @Transactional
    public Mono<Customer> save(Customer customer) {
        final var entity = this.repository.save(new CustomerEntity(customer));
        return entity.map(CustomerEntity::toDomain);
    }

    public Mono<Customer> findById(UUID id) {
        return this.repository
                .findById(id)
                .map(CustomerEntity::toDomain)
                .switchIfEmpty(Mono.error(RegisterNotFoundException::new));
    }


    @Override
    public Mono<Customer> findByEmail(String email) {
        return this.repository.findByEmail(email)
                .map(CustomerEntity::toDomain);
    }

    public Mono<Void> delete(UUID id) {
        return this.repository.deleteById(id);
    }
}
