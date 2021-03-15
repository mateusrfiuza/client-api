package com.customer.api.infrastructure.repository.postgres;

import com.customer.api.domain.WishlistProduct;
import com.customer.api.domain.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WishListProductRepositoryImpl implements WishlistRepository {

    private final WishlistProductDAO dao;

    @Transactional
    public Mono<WishlistProduct> save(UUID clientId, String productId) {
        final var entity = this.dao.save(new WishlistProductEntity(clientId, productId));
        return entity.map(WishlistProductEntity::toDomain);
    }


    @Override
    public Flux<WishlistProduct> findByCustomerId(UUID customerId) {
        return this.dao.findByCustomerId(customerId)
                .map(WishlistProductEntity::toDomain);
    }

    public Mono<Void> deleteById(UUID id) {
        return this.dao.deleteById(id);
    }
}
