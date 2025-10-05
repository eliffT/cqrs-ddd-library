package com.turkcell.libraryappddd.application.loan.mapper;

import com.turkcell.libraryappddd.application.loan.dto.LoanResponse;
import com.turkcell.libraryappddd.domain.model.book.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanResponseMapper {
    public LoanResponse toResponse(Loan loan) {
        return new LoanResponse(
                loan.id().value(),
                loan.userId().value(),
                loan.borrowDate(),
                loan.dueDate(),
                loan.returnDate(),
                loan.status().name()
        );
    }
}