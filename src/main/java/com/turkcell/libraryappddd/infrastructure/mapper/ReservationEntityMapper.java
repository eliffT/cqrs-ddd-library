package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.reservation.Reservation;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.infrastructure.entity.BookEntity;
import com.turkcell.libraryappddd.infrastructure.entity.ReservationEntity;
import com.turkcell.libraryappddd.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservationEntityMapper {

    public ReservationEntity toEntity(Reservation r, BookEntity book, UserEntity user) {
        if (r == null) return null;

        ReservationEntity entity = new ReservationEntity();
        entity.setId(r.id().value());
        entity.setReservationDate(r.reservationDate());
        entity.setExpireAt(r.expireDate());

        entity.setUser(user);
        entity.setBook(book);
        return entity;
    }

    public Reservation toDomain(ReservationEntity entity) {
        if (entity == null) return null;

        return Reservation.rehydrate(
                new DomainId<Reservation>(entity.id()),
                new DomainId<User>(entity.user().id()),
                new DomainId<Book>(entity.book().id()),
                entity.reservationDate(),
                entity.expireAt(),
                entity.status()
        );
    }
}
