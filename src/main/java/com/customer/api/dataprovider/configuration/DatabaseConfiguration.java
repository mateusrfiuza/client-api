package com.customer.api.dataprovider.configuration;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
@EnableR2dbcRepositories
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Bean
    public TransactionalOperator transactionalOperator(final ReactiveTransactionManager reactiveTransactionManager){
        return TransactionalOperator.create(reactiveTransactionManager);
    }

    @Bean
    public ReactiveTransactionManager transactionManager(final ConnectionFactory connectionFactory){
        return new R2dbcTransactionManager(connectionFactory);
    }
}
