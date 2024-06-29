package com.nixiedroid.rest.dto;

import java.util.Set;


public record CoffeeDTO(
        String name,
        boolean hasMilk,
        Set<UserDTOPlain> likedBy
) {
}
