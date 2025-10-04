//package com.turkcell.libraryappddd.application.fine.command;
//
//import com.turkcell.libraryappddd.application.fine.dto.CreatedFineResponse;
//import com.turkcell.libraryappddd.core.cqrs.Command;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Positive;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//public record CreateFineCommand(
//        @NotNull Boolean isPaid,
//        @Positive BigDecimal amount,
//        LocalDate paymentDate)
//        implements Command<CreatedFineResponse> { }
