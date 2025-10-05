package com.turkcell.libraryappddd.application.loan.mapper;

import com.turkcell.libraryappddd.application.loan.command.CreateLoanCommand;
import com.turkcell.libraryappddd.application.loan.dto.CreatedLoanResponse;
import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Loan;
import com.turkcell.libraryappddd.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class CreateLoanMapper {
    public Loan toDomain(CreateLoanCommand command) {
        return Loan.create(
                new DomainId<User>(command.userId()),
                command.loanDays()
        );
    }

    public CreatedLoanResponse toResponse(Loan loan) {
        return new CreatedLoanResponse(
                loan.id().value(),
                loan.userId().value(),
                loan.borrowDate(),
                loan.dueDate()
        );
    }
}
