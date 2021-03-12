package com.client.api.infrastructure.repository.mysql;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table(value = "client")
@Data
@NoArgsConstructor
public class ClientEntity implements Serializable {

    @Id
    private UUID id;

    private String name;

    private String email;


}