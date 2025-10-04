package com.turkcell.libraryappddd.application.author.dto;

import java.util.UUID;

public record CreatedAuthorResponse(UUID id, String fullName) {
}
