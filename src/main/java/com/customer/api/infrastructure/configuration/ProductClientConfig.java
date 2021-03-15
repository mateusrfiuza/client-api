package com.customer.api.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.httpclient.HttpClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProductClientConfig {


    @Value("${threatmetrix-api.url}")
    private String tmxUrl;

    @Value("${webclient.timeout.connection}")
    private Integer defaultConnectionTimeOut;

    @Value("${threatmetrix-api.timeout.ms}")
    private Integer tmxReadTimeout;


    private static final String CONNECTION_NAME = "PRODUCT_CONN";

    @Bean
    public WebClient productReactiveClient(ReactorClientHttpConnector reactorClientHttpConnector) {
        return WebClient.builder()
                .baseUrl(tmxUrl)
                .filter(new WebClientTimeMeterExchangeFilterFunction())
                .clientConnector(reactorClientHttpConnector.connect())
                .build();
    }

}
