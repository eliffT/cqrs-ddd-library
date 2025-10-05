package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.book.Reservation;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.infrastructure.entity.BookEntity;
import com.turkcell.libraryappddd.infrastructure.entity.ReservationEntity;
import com.turkcell.libraryappddd.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReservationEntityMapper {

    public ReservationEntity toEntity(Reservation r) {
        if (r == null) return null;

        ReservationEntity entity = new ReservationEntity();
        entity.setId(r.id().value());
        entity.setReservationDate(r.reservationDate());
        entity.setExpireDate(r.expireDate());
        entity.setStatus(r.status());

        if (r.userId() != null)
            entity.setUser(new UserEntity((r.userId().value())));

        if (r.bookId() != null)
            entity.setBook(new BookEntity((r.bookId().value())));

        return entity;
    }

    public Reservation toDomain(ReservationEntity entity) {
        if (entity == null) return null;

        if (entity.book() == null)
            throw new IllegalStateException("Cannot map Reservation: book is null");
        if (entity.user() == null)
            throw new IllegalStateException("Cannot map Reservation: user is null");

        return Reservation.rehydrate(
                new DomainId<Reservation>(entity.id()),
                new DomainId<User>(entity.user().id()),
                new DomainId<Book>(entity.book().id()),
                entity.reservationDate(),
                entity.expireDate(),
                entity.status()
        );
    }
}
