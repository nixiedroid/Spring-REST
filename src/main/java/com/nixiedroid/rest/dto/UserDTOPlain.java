package com.nixiedroid.rest.dto;

import java.util.UUID;

public record UserDTOPlain(
        UUID uuid,
        String firstName,
        String lastName
) {
}