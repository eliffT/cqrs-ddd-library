package com.turkcell.libraryappddd.application.loan.command;

import com.turkcell.libraryappddd.application.loan.dto.CreatedLoanResponse;
import com.turkcell.libraryappddd.application.loan.mapper.CreateLoanMapper;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.book.Loan;
import com.turkcell.libraryappddd.domain.repository.LoanRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateLoanCommandHandler implements CommandHandler<CreateLoanCommand, CreatedLoanResponse> {

    private final LoanRepository loanRepository;
    private final CreateLoanMapper createLoanMapper;

    public CreateLoanCommandHandler(LoanRepository loanRepository, CreateLoanMapper createLoanMapper) {
        this.loanRepository = loanRepository;
        this.createLoanMapper = createLoanMapper;
    }

    @Override
    public CreatedLoanResponse handle(CreateLoanCommand command) {
        Loan loan = createLoanMapper.toDomain(command);
        loan = loanRepository.save(loan);
        return createLoanMapper.toResponse(loan);
    }
}