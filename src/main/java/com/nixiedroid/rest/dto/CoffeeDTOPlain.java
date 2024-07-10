package com.nixiedroid.rest.dto;

import java.util.UUID;

public record CoffeeDTOPlain(
        UUID uuid,
        String name,
        boolean hasMilk
) {
}
