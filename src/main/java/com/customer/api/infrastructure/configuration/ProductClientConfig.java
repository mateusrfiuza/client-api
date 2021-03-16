package com.customer.api.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProductClientConfig {


    @Value("${product-api.url}")
    private String productUrl;


    @Bean
    public WebClient productReactiveClient() {
        return WebClient.builder()
                .baseUrl(productUrl)
                .filter(new WebClientTimeMeterExchangeFilterFunction())
                .build();
    }

}
