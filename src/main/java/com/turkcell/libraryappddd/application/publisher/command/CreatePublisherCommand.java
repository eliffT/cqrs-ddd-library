package com.turkcell.libraryappddd.application.publisher.command;

import com.turkcell.libraryappddd.application.publisher.dto.CreatedPublisherResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePublisherCommand(
        @NotBlank @Size(min = 2, max = 255) String name)
        implements Command<CreatedPublisherResponse> { }
