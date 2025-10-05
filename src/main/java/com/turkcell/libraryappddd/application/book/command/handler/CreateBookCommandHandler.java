package com.turkcell.libraryappddd.application.book.command.handler;

import com.turkcell.libraryappddd.application.book.command.CreateBookCommand;
import com.turkcell.libraryappddd.application.book.dto.CreatedBookResponse;
import com.turkcell.libraryappddd.application.book.mapper.CreateBookMapper;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateBookCommandHandler implements CommandHandler<CreateBookCommand, CreatedBookResponse> {

    private final BookRepository bookRepository;
    private final CreateBookMapper createBookMapper;

    public CreateBookCommandHandler(BookRepository bookRepository, CreateBookMapper createBookMapper) {
        this.bookRepository = bookRepository;
        this.createBookMapper = createBookMapper;
    }

    @Override
    public CreatedBookResponse handle(CreateBookCommand command) {
        Book book = createBookMapper.toDomain(command);
        book = bookRepository.save(book);
        return createBookMapper.toResponse(book);
    }
}
