package com.nixiedroid.rest.dto;

import java.lang.Long;

public record UserDTOPlain(
        Long id,
        String firstName,
        String lastName
) {
}