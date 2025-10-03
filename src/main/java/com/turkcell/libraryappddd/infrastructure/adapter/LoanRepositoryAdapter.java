package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.loan.Loan;
import com.turkcell.libraryappddd.domain.repository.LoanRepository;
import com.turkcell.libraryappddd.infrastructure.entity.LoanEntity;
import com.turkcell.libraryappddd.infrastructure.jparepository.LoanJpaRepository;
import com.turkcell.libraryappddd.infrastructure.mapper.LoanEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoanRepositoryAdapter implements LoanRepository {
private  final LoanJpaRepository repository;
private final LoanEntityMapper mapper;

    public LoanRepositoryAdapter(LoanJpaRepository repository, LoanEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Loan save(Loan loan) {
        LoanEntity loanEntity = mapper.toEntity(loan);
        loanEntity = repository.save(loanEntity);
        return mapper.toDomain(loanEntity);
    }

    @Override
    public Optional<Loan> findById(DomainId<Loan> loanId) {
        return repository.findById(loanId.value()).map(mapper::toDomain);
    }

    @Override
    public List<Loan> findAll() {

        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Loan> findAllPaged(Integer pageIndex, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageIndex,pageSize))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(DomainId<Loan> loanId) {
    repository.deleteById(loanId.value());
    }
}
