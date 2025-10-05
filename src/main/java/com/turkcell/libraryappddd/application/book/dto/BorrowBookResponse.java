package com.turkcell.libraryappddd.application.book.dto;

import java.util.UUID;

public record BorrowBookResponse (UUID bookId, UUID userId, String title){ }
