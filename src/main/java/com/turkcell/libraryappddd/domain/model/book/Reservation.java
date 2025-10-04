package com.turkcell.libraryappddd.domain.model.book;


import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.enumStatus.ReservationStatus;
import com.turkcell.libraryappddd.domain.model.user.User;

import java.time.LocalDate;

public class Reservation {

    private final DomainId<Reservation> id;
    private final DomainId<User> userId;
    private final DomainId<Book> bookId;
    private LocalDate reservationDate;
    private LocalDate expireDate;
    private ReservationStatus status;

    private Reservation(DomainId<Reservation>  id, DomainId<User> userId, DomainId<Book> bookId, LocalDate reservationDate,
                        LocalDate expireDate, ReservationStatus status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.expireDate = expireDate;
        this.status = status;
    }

    public static Reservation create(DomainId<User> userId, DomainId<Book> bookId, int loanDays){

        if (userId == null) throw new IllegalArgumentException("User cannot be null");
        if (bookId == null) throw new IllegalArgumentException("Book cannot be null");
        LocalDate reservationDate = LocalDate.now();
        if (loanDays <= 0) throw new IllegalArgumentException("Loan days cannot be less than zero");

        LocalDate expireDate = reservationDate.plusDays(loanDays);
        return new Reservation(DomainId.generate(), userId, bookId, reservationDate, expireDate, ReservationStatus.ACTIVE);
    }

    public static Reservation rehydrate(DomainId<Reservation> id, DomainId<User> userId, DomainId<Book> bookId,
                                        LocalDate reservationDate, LocalDate expireDate, ReservationStatus status)
    {
        return new Reservation(id, userId, bookId, reservationDate, expireDate, status);
    }

    public void cancel() {
        if (status != ReservationStatus.ACTIVE)
            throw new IllegalStateException("Only active reservations can be cancelled");
        status = ReservationStatus.CANCELLED;
    }

    public boolean isExpired(LocalDate today) {
        return today.isAfter(expireDate);
    }

    public void markAsExpired() {
        if (!isExpired(LocalDate.now())) {
            throw new IllegalStateException("Cannot mark as expired before expiration date");
        }
        status = ReservationStatus.EXPIRED;
    }

    public DomainId<Reservation>  id() {
        return id;
    }
    public DomainId<User> userId() { return userId; }
    public DomainId<Book> bookId() { return bookId; }
    public LocalDate reservationDate() {
        return reservationDate;
    }
    public LocalDate expireDate() {
        return expireDate;
    }
    public ReservationStatus status() {
        return status;
    }

}
