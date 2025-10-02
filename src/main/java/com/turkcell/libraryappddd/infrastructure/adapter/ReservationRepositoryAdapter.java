package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.reservation.Reservation;
import com.turkcell.libraryappddd.domain.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepository {



    @Override
    public Reservation save(Reservation reservation) {
        return null;
    }

    @Override
    public Optional<Reservation> findById(DomainId<Reservation> reservationId) {
        return Optional.empty();
    }

    @Override
    public List<Reservation> findAll() {
        return List.of();
    }

    @Override
    public List<Reservation> findAllPaged(Integer pageIndex, Integer pageSize) {
        return List.of();
    }

    @Override
    public void delete(DomainId<Reservation> reservationId) {

    }
}
