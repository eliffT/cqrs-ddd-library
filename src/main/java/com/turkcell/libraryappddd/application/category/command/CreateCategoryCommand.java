package com.turkcell.libraryappddd.application.category.command;

import com.turkcell.libraryappddd.application.category.dto.CreatedCategoryResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryCommand(
        @NotBlank @Size(min = 2, max = 100) String name,
        @NotBlank @Size(min = 2, max = 255) String description)
        implements Command<CreatedCategoryResponse> { }