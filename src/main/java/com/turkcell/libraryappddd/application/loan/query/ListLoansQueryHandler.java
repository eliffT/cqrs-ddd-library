package com.turkcell.libraryappddd.application.loan.query;

import com.turkcell.libraryappddd.application.loan.dto.LoanResponse;
import com.turkcell.libraryappddd.application.loan.mapper.LoanResponseMapper;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import com.turkcell.libraryappddd.domain.repository.LoanRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListLoansQueryHandler implements QueryHandler<ListLoansQuery, List<LoanResponse>> {

    private final LoanRepository loanRepository;
    private final LoanResponseMapper loanResponseMapper;

    public ListLoansQueryHandler(LoanRepository loanRepository, LoanResponseMapper loanResponseMapper) {
        this.loanRepository = loanRepository;
        this.loanResponseMapper = loanResponseMapper;
    }

    @Override
    public List<LoanResponse> handle(ListLoansQuery query) {
        return loanRepository
                .findAll()
                .stream()
                .map(loanResponseMapper::toResponse)
                .toList();
    }
}
