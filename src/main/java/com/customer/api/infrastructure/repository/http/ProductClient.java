package com.customer.api.infrastructure.repository.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductClient {

    private static final String SESSION_QUERY = "/api/product/";

    private WebClient productReactiveClient;

    @Value("${threatmetrix-api.timeout.ms}")
    private Long tmxDefaultTimeout;

    public Mono<ProductResponse> getProduct(final String productId) {
        return productReactiveClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(SESSION_QUERY + productId)
                        .build())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .timeout(Duration.ofMillis(3000))
                .doOnNext(value -> log.info("M=getProduct productId={} response={}", productId, value))
                .doOnError(error -> log.error("M=getProduct productId={}", productId, error));
    }

}
