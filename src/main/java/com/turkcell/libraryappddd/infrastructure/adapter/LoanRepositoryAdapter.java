package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.loan.Loan;
import com.turkcell.libraryappddd.domain.repository.LoanRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoanRepositoryAdapter implements LoanRepository {

    @Override
    public Loan save(Loan loan) {
        return null;
    }

    @Override
    public Optional<Loan> findById(DomainId<Loan> loanId) {
        return Optional.empty();
    }

    @Override
    public List<Loan> findAll() {
        return List.of();
    }

    @Override
    public List<Loan> findAllPaged(Integer pageIndex, Integer pageSize) {
        return List.of();
    }

    @Override
    public void delete(DomainId<Loan> loanId) {

    }
}
