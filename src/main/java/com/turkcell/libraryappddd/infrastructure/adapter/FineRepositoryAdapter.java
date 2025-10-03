package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.fine.Fine;
import com.turkcell.libraryappddd.domain.repository.FineRepository;
import com.turkcell.libraryappddd.infrastructure.entity.FineEntity;
import com.turkcell.libraryappddd.infrastructure.jparepository.FineJpaRepository;
import com.turkcell.libraryappddd.infrastructure.mapper.FineEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FineRepositoryAdapter implements FineRepository {
    private final FineJpaRepository repository;
    private final FineEntityMapper mapper;

    public FineRepositoryAdapter(FineJpaRepository repository, FineEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Fine save(Fine fine) {
        FineEntity fineEntity = mapper.toEntity(fine);
        fineEntity = repository.save(fineEntity);
        return mapper.toDomain(fineEntity);
    }

    @Override
    public Optional<Fine> findById(DomainId<Fine> fineId) {
       return repository.findById(fineId.value()).map(mapper::toDomain);
    }

    @Override
    public List<Fine> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Fine> findAllPaged(Integer pageIndex, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageIndex,pageSize))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(DomainId<Fine> fineId) {
        repository.deleteById(fineId.value());

    }
}
