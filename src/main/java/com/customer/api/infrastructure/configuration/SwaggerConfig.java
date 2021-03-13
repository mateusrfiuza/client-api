package com.customer.api.infrastructure.configuration;

import com.customer.api.CustomerApplication;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(CustomerApplication.class.getPackage().getName()))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
            .title("Customer API")
            .contact(new Contact("Mateus Fiuza", "", "mail@mail.com"))
            .build();
    }


    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
            .deepLinking(true)
            .displayOperationId(true)
            .defaultModelsExpandDepth(-1)
            .defaultModelExpandDepth(1)
            .defaultModelRendering(ModelRendering.MODEL)
            .displayRequestDuration(true)
            .docExpansion(DocExpansion.NONE)
            .filter(false)
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(false)
            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
            .validatorUrl(null)
            .build();
    }

}
