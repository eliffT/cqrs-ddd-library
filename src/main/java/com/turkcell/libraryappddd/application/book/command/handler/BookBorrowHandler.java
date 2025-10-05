package com.turkcell.libraryappddd.application.book.command.handler;

import com.turkcell.libraryappddd.application.book.command.BookBorrowCommand;
import com.turkcell.libraryappddd.application.book.command.CreateBookCommand;
import com.turkcell.libraryappddd.application.book.dto.BorrowBookResponse;
import com.turkcell.libraryappddd.application.book.dto.CreatedBookResponse;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.book.Loan;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.domain.repository.BookRepository;
import com.turkcell.libraryappddd.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookBorrowHandler implements CommandHandler<BookBorrowCommand, BorrowBookResponse> {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookBorrowHandler(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BorrowBookResponse handle(BookBorrowCommand command) {
       Book book = bookRepository.findById(DomainId.fromString(command.bookId()))
               .orElseThrow(()-> new IllegalArgumentException("Book not found"));
       User user = userRepository.findById(DomainId.fromString(command.userId()))
               .orElseThrow(()-> new IllegalArgumentException("User not found"));
       Loan loan = book.borrow(DomainId.fromString(command.userId()), user.getLoanDays());
       bookRepository.save(book);
       return new BorrowBookResponse(book.id().value(),user.id().value(),book.title());
    }


}
