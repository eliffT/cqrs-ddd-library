package com.turkcell.libraryappddd.application.book.command.handler;

import com.turkcell.libraryappddd.application.book.command.BookReturnCommand;
import com.turkcell.libraryappddd.application.book.dto.BookResponse;
import com.turkcell.libraryappddd.application.book.mapper.BookResponseMapper;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.book.Loan;
import com.turkcell.libraryappddd.domain.repository.BookRepository;
import com.turkcell.libraryappddd.domain.repository.LoanRepository;
import org.springframework.stereotype.Component;

@Component
public class BookReturnHandler implements CommandHandler<BookReturnCommand, BookResponse> {
    private final BookRepository bookRepository;
    private final BookResponseMapper bookResponseMapper;
    private final LoanRepository loanRepository;

    public BookReturnHandler(BookRepository bookRepository, BookResponseMapper bookResponseMapper, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.bookResponseMapper = bookResponseMapper;
        this.loanRepository = loanRepository;
    }


    @Override
    public BookResponse handle(BookReturnCommand command) {
       Book book = bookRepository.findById(DomainId.fromString(command.bookId())).
               orElseThrow(()->new IllegalArgumentException("Book not found!"));

       Loan loan = loanRepository.findById(DomainId.fromString(command.loanId())).
               orElseThrow(()->new IllegalArgumentException("Loan not found!"));

       book.returnLoan(DomainId.fromString(command.loanId()));
       book = bookRepository.save(book);
       return bookResponseMapper.toResponse(book);
    }
}
