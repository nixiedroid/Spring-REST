package com.nixiedroid.rest.dto;

import java.lang.Long;

public record CoffeeDTOPlain(
        Long id,
        String name,
        boolean hasMilk
) {
}
