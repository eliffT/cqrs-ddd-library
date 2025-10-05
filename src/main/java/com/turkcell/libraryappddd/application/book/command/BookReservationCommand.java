package com.turkcell.libraryappddd.application.book.command;

import jakarta.validation.constraints.NotNull;

public record BookReservationCommand (
        @NotNull String userId,
        @NotNull String bookId)
{ }
