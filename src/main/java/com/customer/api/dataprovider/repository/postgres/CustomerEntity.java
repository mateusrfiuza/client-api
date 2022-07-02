package com.customer.api.dataprovider.repository.postgres;

import com.customer.api.domain.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table(value = "customer")
@Data
@NoArgsConstructor
public class CustomerEntity implements Serializable {

    @Id
    @Column("id")
    private UUID id;

    @Column("name")
    private String name;

    @Column("email")
    private String email;

    public CustomerEntity(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
    }

    public Customer toDomain() {
        final var customer = new Customer();

        customer.setId(this.id);
        customer.setName(this.name);
        customer.setEmail(this.email);

        return customer;
    }


}