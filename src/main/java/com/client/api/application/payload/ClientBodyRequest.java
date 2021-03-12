package com.client.api.application.payload;

import com.client.api.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientBodyRequest {

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    public Client toDomain() {
        var client = new Client();
        client.setEmail(this.email);
        client.setName(this.name);

        return client;
    }


}
