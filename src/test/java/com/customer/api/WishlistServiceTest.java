package com.customer.api;

import com.customer.api.domain.WishlistProduct;
import com.customer.api.domain.repository.ProductRepository;
import com.customer.api.domain.repository.WishlistRepository;
import com.customer.api.domain.service.CustomerService;
import com.customer.api.domain.service.WishlistService;
import com.customer.api.domain.service.exception.CustomerNotFoundException;
import com.customer.api.domain.service.exception.ProductAlreadyRegisteredException;
import com.customer.api.infrastructure.repository.exception.RegisterNotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WishlistServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private WishlistService wishlistService;


    @Test
    public void check_product_already_registered() {
        //Given
        when(wishlistRepository.findByCustomerIdAndProductId(any(), any())).thenReturn(Mono.just(new WishlistProduct()));

        // When
        var mono = wishlistService.checkProductAlreadyRegistered(UUID.randomUUID(), "teste");

        // Then
        StepVerifier.create(mono)
                .expectErrorMatches(throwable -> throwable instanceof ProductAlreadyRegisteredException)
                .verify();

    }

}
