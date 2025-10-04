package com.turkcell.libraryappddd.application.category.dto;

import java.util.UUID;

public record CategoryResponse(UUID id, String name, String description) { }
