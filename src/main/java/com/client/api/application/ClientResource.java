package com.client.api.application;


import com.client.api.domain.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController("/clients")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create client")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Account"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 409, message = "Account Already Registered")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<UUID>> create(@RequestBody @Valid final ClientBodyRequest request)   {
        return service.createClient(request.toDomain())
                .map(client -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(client.getId()));

    }

//    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Get client")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Account Found"),
//            @ApiResponse(code = 400, message = "Invalid input data"),
//            @ApiResponse(code = 404, message = "Account Not Found")
//    })
//    public Mono<ResponseEntity<Void>> get(@PathVariable final String email) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(accountResponseMapper.from(accountService.getAccount(accountId)));
//
//    }
//
//    @DeleteMapping
//    @ApiOperation(value = "Delete client")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Account Found"),
//            @ApiResponse(code = 400, message = "Invalid input data"),
//            @ApiResponse(code = 404, message = "Account Not Found")
//    })
//    public Mono<ResponseEntity<Void>> delete(@PathVariable final Long accountId)  {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(accountResponseMapper.from(accountService.getAccount(accountId)));
//
//    }
//
//    @PutMapping
//    @ApiOperation(value = "Update client")
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
