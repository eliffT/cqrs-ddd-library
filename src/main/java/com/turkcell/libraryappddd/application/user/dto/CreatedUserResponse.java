package com.turkcell.libraryappddd.application.user.dto;

import java.util.UUID;

public record CreatedUserResponse(UUID id, String fullName, String message) {
}
