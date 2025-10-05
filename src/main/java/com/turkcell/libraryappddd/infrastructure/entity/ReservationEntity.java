package com.turkcell.libraryappddd.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkcell.libraryappddd.domain.model.book.enumStatus.ReservationStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.ACTIVE;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    public UUID id() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate reservationDate() {
        return reservationDate;
    }
    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus status() {
        return status;
    }
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDate expireDate() {
        return expireDate;
    }
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public BookEntity book() {
        return book;
    }
    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity user() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
}
