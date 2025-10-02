package com.turkcell.libraryappddd.domain.model.reservation;


import com.turkcell.libraryappddd.domain.model.DomainId;

import java.time.LocalDate;

public class Reservation {
    private final DomainId<Reservation> id;
    private LocalDate reservationDate;
    private static LocalDate creationDate;
    private LocalDate expireDate;
    private static ReservationStatus status;

    private Reservation(DomainId<Reservation>  id, LocalDate reservationDate, LocalDate expireDate) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.expireDate = expireDate;
    }

    public static Reservation create(LocalDate reservationDate, LocalDate expireDate){
        setCreationDate();
        activate();
        return new Reservation(DomainId.generate(), reservationDate, expireDate);
    }

    public static Reservation rehydrate(DomainId<Reservation>  id, LocalDate reservationDate, LocalDate expireDate)
    {
        return new Reservation(id,reservationDate,expireDate);
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public static void setCreationDate() {
        creationDate = LocalDate.now();
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public static void activate(){
        status = ReservationStatus.ACTIVE;
    }

    public static void deactivate(){
        status = ReservationStatus.CANCELLED;
    }

    public DomainId<Reservation>  id() {
        return id;
    }
    public LocalDate reservationDate() {
        return reservationDate;
    }
    public LocalDate creationDate() {
        return creationDate;
    }
    public LocalDate expireDate() {
        return expireDate;
    }
    public ReservationStatus status() {
        return status;
    }

}
