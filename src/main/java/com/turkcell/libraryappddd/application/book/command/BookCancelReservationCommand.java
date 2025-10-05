package com.turkcell.libraryappddd.application.book.command;

import jakarta.validation.constraints.NotNull;

public record BookCancelReservationCommand(
         @NotNull String reservationId)
{ }
