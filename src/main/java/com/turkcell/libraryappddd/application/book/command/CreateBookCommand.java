package com.turkcell.libraryappddd.application.book.command;

import com.turkcell.libraryappddd.application.author.dto.AuthorResponse;
import com.turkcell.libraryappddd.application.author.dto.CreatedAuthorResponse;
import com.turkcell.libraryappddd.application.book.dto.CreatedBookResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.author.Author;
import com.turkcell.libraryappddd.domain.model.book.enumStatus.BookStatus;
import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateBookCommand(
        @NotNull String authorId,
        @NotNull String publisherId,
        @NotNull String categoryId,
        @NotBlank @Size(min = 3, max = 255) String title,
        @NotNull @Min(1501) Integer year,
        @NotBlank @Size(min = 3, max = 14) String language,
        @NotNull Integer totalCopies,
        @NotNull @Positive BigDecimal price)
        implements Command<CreatedBookResponse> {
    @Override
    public BigDecimal price() {
        return price;


    }
}