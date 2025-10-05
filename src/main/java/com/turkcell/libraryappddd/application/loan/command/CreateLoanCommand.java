package com.turkcell.libraryappddd.application.loan.command;

import com.turkcell.libraryappddd.application.loan.dto.CreatedLoanResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateLoanCommand(
        @NotNull UUID userId,
        @Min(1) int loanDays
) implements Command<CreatedLoanResponse> { }
