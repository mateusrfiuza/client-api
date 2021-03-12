package com.client.api.infrastructure.configuration;

import com.client.api.ClientApplication;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

/**
 * Configurações para habilitar e configurar o swagger no projeto.
 */
@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(ClientApplication.class.getPackage().getName()))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
            .title("Security Risk Engine")
            .description("APIs de análise de risco")
            .contact(new Contact("Alcatraz Team", "", "l-dev-pagseguro-alcatraz@uolinc.com"))
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
