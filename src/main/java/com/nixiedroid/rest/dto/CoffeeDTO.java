package com.nixiedroid.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.lang.Long;


public record CoffeeDTO(
        Long id,
        @NotNull
        @Size(min=3, message="Coffee name must be at least 3 characters long")
        String name,
        boolean hasMilk,
        @NotNull(message = "Liked By must not be null")
        Set<UserDTOPlain> likedBy
) {
}
