package com.customer.api.application;


import com.customer.api.application.payload.CustomerResponse;
import com.customer.api.domain.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController("/customers")
@RequiredArgsConstructor
public class CustomerResource {

    private final CustomerService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create customer")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Account"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 409, message = "Account Already Registered")
    })
    public Mono<ResponseEntity<UUID>> create(@RequestBody @Valid final CustomerBodyRequest request)   {
        return service.create(request.toDomain())
                .map(customer -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(customer.getId()));

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer Found", response = ClientResponse.class),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 404, message = "Customer Not Found")
    })
    public Mono<ResponseEntity<CustomerResponse>> get(@PathVariable final UUID id) {
        return service.get(id)
                .map(customer -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(new CustomerResponse(customer))
                );

    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Delete customer")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Operation performed successfully"),
            @ApiResponse(code = 400, message = "Invalid input data")
    })
    public Mono<ResponseEntity<Void>> delete(@PathVariable final UUID id) {
        return service.delete(id)
                .map(aVoid -> ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .build()
                );
    }

//    @PutMapping
//    @ApiOperation(value = "Update customer")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Account Found"),
//            @ApiResponse(code = 400, message = "Invalid input data"),
//            @ApiResponse(code = 404, message = "Account Not Found")
//    })
//    public Mono<ResponseEntity<Void>> update(@PathVariable final Long accountId) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(accountResponseMapper.from(accountService.getAccount(accountId)));
//
//    }

}
