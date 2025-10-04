package com.turkcell.libraryappddd.application.book.dto;

import com.turkcell.libraryappddd.domain.model.book.enumStatus.BookStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatedBookResponse(UUID id, UUID authorId, UUID categoryId,
                                  String title, Integer year, String language,
                                  Integer totalCopies,
                                  BigDecimal price) {
}
