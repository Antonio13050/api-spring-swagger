package com.swagger.api.apispringswagger.model.transport.operations;

import jakarta.validation.constraints.NotBlank;

public record UpdatePersonForm(@NotBlank String name,
                               @NotBlank String email) {
}
