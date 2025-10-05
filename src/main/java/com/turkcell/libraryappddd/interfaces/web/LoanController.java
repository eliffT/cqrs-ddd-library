package com.turkcell.libraryappddd.interfaces.web;

import com.turkcell.libraryappddd.application.loan.command.CreateLoanCommand;
import com.turkcell.libraryappddd.application.loan.dto.CreatedLoanResponse;
import com.turkcell.libraryappddd.application.loan.dto.LoanResponse;
import com.turkcell.libraryappddd.application.loan.query.ListLoansQuery;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.repository.LoanRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loans")
@Validated
public class LoanController {

    private final QueryHandler<ListLoansQuery, List<LoanResponse>> listLoansQueryHandler;
    private final CommandHandler<CreateLoanCommand, CreatedLoanResponse> createLoanCommandHandler;
    private final LoanRepository loanRepository;

    public LoanController(QueryHandler<ListLoansQuery, List<LoanResponse>> listLoansQueryHandler,
            CommandHandler<CreateLoanCommand, CreatedLoanResponse> createLoanCommandHandler,
            LoanRepository loanRepository)
    {
        this.listLoansQueryHandler = listLoansQueryHandler;
        this.createLoanCommandHandler = createLoanCommandHandler;
        this.loanRepository = loanRepository;
    }

    @GetMapping
    public List<LoanResponse> getLoans(@Valid ListLoansQuery query) {
        return listLoansQueryHandler.handle(query);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedLoanResponse createLoan(@Valid @RequestBody CreateLoanCommand command) {
        return createLoanCommandHandler.handle(command);
    }

}
