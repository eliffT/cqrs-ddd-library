package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.loan.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    Loan save(Loan loan);
    Optional<Loan> findById(DomainId<Loan> loanId);
    List<Loan> findAll();
    List<Loan> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(DomainId<Loan> loanId);
}
