package com.nixiedroid.rest.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.lang.Long;

public record UserDTO(
        Long id,
        @NotNull
        @Size(min=1, message="First name must be at least 1 characters long")
        String firstName,
        @NotNull
        @Size(min=1, message="Last name must be at least 1 characters long")
        String lastName,
        Set<CoffeeDTOPlain> favCoffees
) {
}
