package com.customer.api.entrypoint.rest;


import com.customer.api.entrypoint.rest.payload.WishlistProductBodyRequest;
import com.customer.api.entrypoint.rest.payload.WishlistProductResponse;
import com.customer.api.domain.service.WishlistService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class WishlistResource {

    private final WishlistService service;

    private static final String DEFAULT_URI = "/customers/{client-id}/wishlists";


    @PostMapping(value = DEFAULT_URI, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add an product in wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Account"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 409, message = "Account Already Registered")
    })
    public Mono<ResponseEntity<UUID>> create(@PathVariable("client-id") final UUID clientId, @RequestBody @Valid final WishlistProductBodyRequest request)   {
        return service.addProduct(clientId, request.getProductId())
                .map(wishlistProduct -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(wishlistProduct.getId()));

    }

    @GetMapping(value = DEFAULT_URI)
    @ApiOperation(value = "Get wishlist products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation performed successfully", response = WishlistProductResponse.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid input data")
    })
    public Flux<WishlistProductResponse> getItems(@PathVariable("client-id") final UUID clientId) {
        return service.getItems(clientId)
                .map(WishlistProductResponse::new);
    }

    @DeleteMapping(value = "/customers/wishlists/{id}")
    @ApiOperation(value = "Remove an product in Wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Operation performed successfully"),
            @ApiResponse(code = 400, message = "Invalid input data")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> delete(@PathVariable final UUID id) {
        return service.delete(id)
                .map(aVoid -> ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .build()
                );
    }

}
