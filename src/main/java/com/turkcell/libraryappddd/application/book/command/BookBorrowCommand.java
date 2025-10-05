package com.turkcell.libraryappddd.application.book.command;

import com.turkcell.libraryappddd.application.book.dto.BorrowBookResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import jakarta.validation.constraints.NotNull;

public record BookBorrowCommand (
    @NotNull String userId,
    @NotNull String bookId
    ) implements Command<BorrowBookResponse> {}

