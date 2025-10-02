package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.reservation.Reservation;
import com.turkcell.libraryappddd.domain.model.reservation.ReservationId;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(ReservationId reservationId);
    List<Reservation> findAll();
    List<Reservation> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(ReservationId reservationId);
}
