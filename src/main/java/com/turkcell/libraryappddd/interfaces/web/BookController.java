package com.turkcell.libraryappddd.interfaces.web;

import com.turkcell.libraryappddd.application.author.command.CreateAuthorCommand;
import com.turkcell.libraryappddd.application.author.dto.AuthorResponse;
import com.turkcell.libraryappddd.application.author.dto.CreatedAuthorResponse;
import com.turkcell.libraryappddd.application.author.query.ListAuthorsQuery;
import com.turkcell.libraryappddd.application.book.command.BookBorrowCommand;
import com.turkcell.libraryappddd.application.book.command.CreateBookCommand;
import com.turkcell.libraryappddd.application.book.dto.BookResponse;
import com.turkcell.libraryappddd.application.book.dto.BorrowBookResponse;
import com.turkcell.libraryappddd.application.book.dto.CreatedBookResponse;
import com.turkcell.libraryappddd.application.book.query.ListBooksQuery;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Validated
public class BookController {

    private final QueryHandler<ListBooksQuery, List<BookResponse>> listBooksQueryListQueryHandler;
    private final CommandHandler<CreateBookCommand, CreatedBookResponse> createBookComamndHandler;
    private final CommandHandler<BookBorrowCommand, BorrowBookResponse> bookBorrowCommandHandler;

    public BookController(QueryHandler<ListBooksQuery, List<BookResponse>> listBooksQueryListQueryHandler, CommandHandler<CreateBookCommand, CreatedBookResponse> createBookComamndHandler, CommandHandler<BookBorrowCommand, BorrowBookResponse> bookBorrowCommandHandler) {
        this.listBooksQueryListQueryHandler = listBooksQueryListQueryHandler;
        this.createBookComamndHandler = createBookComamndHandler;
        this.bookBorrowCommandHandler = bookBorrowCommandHandler;
    }

    @GetMapping
    public List<BookResponse> getBooks(@Valid ListBooksQuery query)
    {
        return listBooksQueryListQueryHandler.handle(query);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBookResponse createBook(@Valid @RequestBody CreateBookCommand command){
        return createBookComamndHandler.handle(command);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowBookResponse borrow(@Valid @RequestBody BookBorrowCommand command ){
        return bookBorrowCommandHandler.handle(command);
    }
}
