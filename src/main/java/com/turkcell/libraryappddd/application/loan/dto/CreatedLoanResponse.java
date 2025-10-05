package com.turkcell.libraryappddd.application.loan.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CreatedLoanResponse(
        UUID id,
        UUID userId,
        LocalDate borrowDate,
        LocalDate dueDate
) { }