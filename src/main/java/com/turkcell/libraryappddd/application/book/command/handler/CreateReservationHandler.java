package com.turkcell.libraryappddd.application.book.command.handler;

import com.turkcell.libraryappddd.application.book.command.BookReservationCommand;
import com.turkcell.libraryappddd.application.book.dto.ReservationResponse;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.book.Reservation;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.domain.repository.BookRepository;
import com.turkcell.libraryappddd.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateReservationHandler implements CommandHandler<BookReservationCommand, ReservationResponse> {

    private final BookRepository bookRepository;


    public CreateReservationHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    @Override
    public ReservationResponse handle(BookReservationCommand command) {
        Book book = bookRepository.findById(DomainId.fromString(command.bookId())).
                orElseThrow(() -> new IllegalArgumentException("Book not found!"));



        Reservation reservation = book.reserve(DomainId.fromString(command.userId()),
                command.reservationDays());

        book = bookRepository.save(book);
        return new ReservationResponse(reservation.id().value(),reservation.userId().value(),
                reservation.bookId().value(),
                reservation.reservationDate(),
                reservation.expireDate(),
                reservation.status());

    }
}
