package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.book.Loan;
import com.turkcell.libraryappddd.domain.repository.LoanRepository;
import com.turkcell.libraryappddd.infrastructure.entity.BookEntity;
import com.turkcell.libraryappddd.infrastructure.entity.LoanEntity;
import com.turkcell.libraryappddd.infrastructure.jparepository.LoanJpaRepository;
import com.turkcell.libraryappddd.infrastructure.mapper.BookEntityMapper;
import com.turkcell.libraryappddd.infrastructure.mapper.LoanEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoanRepositoryAdapter implements LoanRepository {
private  final LoanJpaRepository repository;
private final LoanEntityMapper loanEntityMapper;
private final BookEntityMapper bookEntityMapper;

    public LoanRepositoryAdapter(LoanJpaRepository repository, LoanEntityMapper loanEntityMapper, BookEntityMapper bookEntityMapper) {
        this.repository = repository;
        this.loanEntityMapper = loanEntityMapper;
        this.bookEntityMapper = bookEntityMapper;
    }



    @Override
    public Optional<Loan> findById(DomainId<Loan> loanId) {
        return repository.findById(loanId.value()).map(loanEntityMapper::toDomain);
    }

    @Override
    public List<Loan> findAll() {

        return repository.findAll().stream().map(loanEntityMapper::toDomain).toList();
    }

    @Override
    public List<Loan> findAllPaged(Integer pageIndex, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageIndex,pageSize))
                .stream()
                .map(loanEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(DomainId<Loan> loanId) {
    repository.deleteById(loanId.value());
    }
}
