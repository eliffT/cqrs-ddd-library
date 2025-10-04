package com.turkcell.libraryappddd.application.book.query;

import com.turkcell.libraryappddd.application.book.dto.BookResponse;
import com.turkcell.libraryappddd.application.book.mapper.BookResponseMapper;
import com.turkcell.libraryappddd.core.cqrs.Command;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import com.turkcell.libraryappddd.domain.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListBooksQueryHandler implements QueryHandler<ListBooksQuery, List<BookResponse>>{

    private final BookRepository bookRepository;
    private final BookResponseMapper bookResponseMapper;

    public ListBooksQueryHandler(BookRepository bookRepository, BookResponseMapper bookResponseMapper) {
        this.bookRepository = bookRepository;
        this.bookResponseMapper = bookResponseMapper;
    }

    @Override
    public List<BookResponse> handle(ListBooksQuery query) {
        return bookRepository.findAll().stream().map(bookResponseMapper::toResponse).toList();
    }


}