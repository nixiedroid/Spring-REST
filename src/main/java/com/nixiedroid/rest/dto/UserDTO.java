package com.nixiedroid.rest.dto;

import java.util.Set;

public record UserDTO(
        String firstName,
        String lastName,
        Set<CoffeeDTOPlain> favCoffees
) {
}
