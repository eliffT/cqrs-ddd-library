package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.book.Fine;
import com.turkcell.libraryappddd.domain.model.book.Loan;

import java.util.List;

public interface FineRepository {
    Fine save(Fine fine, Loan loan);
    List<Fine> findByLoan(Loan loan);
    void deleteByLoan(Loan loan);    // loan bazlı
}
