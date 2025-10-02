package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.fine.Fine;

import java.util.List;
import java.util.Optional;

public interface FineRepository {
    Fine save(Fine fine);
    Optional<Fine> findById(DomainId<Fine> fineId);
    List<Fine> findAll();
    List<Fine> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(DomainId<Fine> fineId);
}
