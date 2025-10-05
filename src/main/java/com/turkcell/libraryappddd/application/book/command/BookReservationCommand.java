package com.turkcell.libraryappddd.application.book.command;

import com.turkcell.libraryappddd.application.book.dto.ReservationResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import jakarta.validation.constraints.NotNull;

public record BookReservationCommand (
        @NotNull String userId,
        @NotNull String bookId,
        @NotNull Integer reservationDays) implements Command<ReservationResponse>
{ }
