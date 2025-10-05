package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    Optional<Reservation> findById(DomainId<Reservation> reservationId);
    List<Reservation> findAll();
    List<Reservation> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(DomainId<Reservation> reservationId);
}
