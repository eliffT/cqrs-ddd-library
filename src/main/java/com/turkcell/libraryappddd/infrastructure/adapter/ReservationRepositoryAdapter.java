package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Reservation;
import com.turkcell.libraryappddd.domain.repository.ReservationRepository;
import com.turkcell.libraryappddd.infrastructure.entity.ReservationEntity;
import com.turkcell.libraryappddd.infrastructure.jparepository.ReservationJpaRepository;
import com.turkcell.libraryappddd.infrastructure.mapper.ReservationEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepository {
private final ReservationJpaRepository repository;
private final ReservationEntityMapper mapper;

    public ReservationRepositoryAdapter(ReservationJpaRepository repository, ReservationEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity reservationEntity = mapper.toEntity(reservation);
        reservationEntity = repository.save(reservationEntity);
        return mapper.toDomain(reservationEntity);
    }

    @Override
    public Optional<Reservation> findById(DomainId<Reservation> reservationId) {
       return repository.findById(reservationId.value()).map(mapper::toDomain);
    }

    @Override
    public List<Reservation> findAll() {

        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Reservation> findAllPaged(Integer pageIndex, Integer pageSize) {

        return repository.findAll(PageRequest.of(pageIndex,pageSize))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(DomainId<Reservation> reservationId) {
        repository.deleteById(reservationId.value());
    }
}
