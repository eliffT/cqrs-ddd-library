package com.turkcell.libraryappddd.application.book.dto;

import com.turkcell.libraryappddd.domain.model.book.enumStatus.ReservationStatus;

import java.time.LocalDate;
import java.util.UUID;

public record ReservationResponse(UUID id, UUID userId, UUID bookId,
                                  LocalDate reservationDate, LocalDate expireDate,
                                  ReservationStatus status) {
}
