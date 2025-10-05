package com.turkcell.libraryappddd.application.book.command.handler;

import com.turkcell.libraryappddd.application.book.command.BookCancelReservationCommand;

import com.turkcell.libraryappddd.application.book.dto.ReservationResponse;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;

import com.turkcell.libraryappddd.domain.model.book.Reservation;
import com.turkcell.libraryappddd.domain.repository.BookRepository;
import com.turkcell.libraryappddd.domain.repository.ReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class CancelReservationHandler implements CommandHandler<BookCancelReservationCommand, ReservationResponse> {
    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;


    public CancelReservationHandler(BookRepository bookRepository, ReservationRepository reservationRepository) {
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public ReservationResponse handle(BookCancelReservationCommand command) {
        Book book = bookRepository.findById(DomainId.fromString(command.bookId())).
                orElseThrow(() -> new IllegalArgumentException("Book not found!"));

        Reservation reservation = reservationRepository.findById(DomainId.fromString(command.reservationId())).
                orElseThrow(() -> new IllegalArgumentException("Reservation not found!"));
        book.cancelReservation(DomainId.fromString(command.reservationId()));
        book = bookRepository.save(book);

        return new ReservationResponse(reservation.id().value(), reservation.userId().value(),
                reservation.bookId().value(), reservation.reservationDate(),
                reservation.expireDate(), reservation.status());
    }
}
