package com.swagger.api.apispringswagger.model.transport.operations;

import jakarta.validation.constraints.NotBlank;

public record CreatePersonForm(@NotBlank String name,
                               @NotBlank String email,
                               @NotBlank String password) {
}
