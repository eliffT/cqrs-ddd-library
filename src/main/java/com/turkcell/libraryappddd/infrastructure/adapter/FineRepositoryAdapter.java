package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.book.Fine;
import com.turkcell.libraryappddd.domain.model.book.Loan;
import com.turkcell.libraryappddd.domain.repository.FineRepository;
import com.turkcell.libraryappddd.infrastructure.entity.FineEntity;
import com.turkcell.libraryappddd.infrastructure.entity.LoanEntity;
import com.turkcell.libraryappddd.infrastructure.jparepository.FineJpaRepository;
import com.turkcell.libraryappddd.infrastructure.mapper.FineEntityMapper;
import com.turkcell.libraryappddd.infrastructure.mapper.LoanEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FineRepositoryAdapter implements FineRepository {

    private final FineJpaRepository repository;
    private final FineEntityMapper fineEntityMapper;
    private final LoanEntityMapper loanEntityMapper;

    public FineRepositoryAdapter(FineJpaRepository repository, FineEntityMapper fineEntityMapper, LoanEntityMapper loanEntityMapper) {
        this.repository = repository;
        this.fineEntityMapper = fineEntityMapper;
        this.loanEntityMapper = loanEntityMapper;
    }

    @Override
    public Fine save(Fine fine, Loan loan) {
        LoanEntity loanEntity = loanEntityMapper.toEntity(loan);
        FineEntity entity = fineEntityMapper.toEntity(fine, loanEntity);
        repository.save(entity);
        return fine;
    }

    @Override
    public List<Fine> findByLoan(Loan loan) {
        LoanEntity loanEntity = loanEntityMapper.toEntity(loan);
        return repository.findByLoan(loanEntity).stream()
                .map(fineEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByLoan(Loan loan) {
        repository.deleteByLoan(loanEntityMapper.toEntity(loan));
    }

}
