package com.turkcell.libraryappddd.application.book.command;

import com.turkcell.libraryappddd.application.book.dto.BookResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import jakarta.validation.constraints.NotNull;

public record BookReturnCommand (
        @NotNull String loanId, @NotNull String bookId) implements Command<BookResponse>
{ }
