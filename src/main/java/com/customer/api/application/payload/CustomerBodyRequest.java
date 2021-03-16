package com.customer.api.application.payload;

import com.customer.api.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBodyRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    public Customer toDomain() {
        var customer = new Customer();
        customer.setEmail(this.email);
        customer.setName(this.name);

        return customer;
    }


}
