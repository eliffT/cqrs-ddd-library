package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.fine.Fine;
import com.turkcell.libraryappddd.domain.repository.FineRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FineRepositoryAdapter implements FineRepository {


    @Override
    public Fine save(Fine fine) {
        return null;
    }

    @Override
    public Optional<Fine> findById(DomainId<Fine> fineId) {
        return Optional.empty();
    }

    @Override
    public List<Fine> findAll() {
        return List.of();
    }

    @Override
    public List<Fine> findAllPaged(Integer pageIndex, Integer pageSize) {
        return List.of();
    }

    @Override
    public void delete(DomainId<Fine> fineId) {

    }
}
