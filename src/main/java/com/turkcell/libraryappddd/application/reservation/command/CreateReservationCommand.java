package com.turkcell.libraryappddd.application.reservation.command;

import com.turkcell.libraryappddd.application.author.dto.CreatedAuthorResponse;
import com.turkcell.libraryappddd.application.reservation.dto.CreatedReservationResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateReservationCommand (
    @NotNull String userId,
    @NotNull String bookId
    )
            implements Command<CreatedReservationResponse>{ }
