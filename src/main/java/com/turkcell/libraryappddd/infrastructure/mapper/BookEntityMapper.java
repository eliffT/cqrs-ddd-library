package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.infrastructure.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookEntityMapper {
    public BookEntity toEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.id().value());
        bookEntity.setIsbn(book.isbn());
        bookEntity.setTitle(book.title());
        bookEntity.setYear(book.year());
        bookEntity.setLanguage(book.language());
        bookEntity.setTotalCopies(book.totalCopies());
        bookEntity.setAvailableCopies(book.availableCopies());
        bookEntity.setStatus(book.status());
        return bookEntity;
    }

    public Book toDomain(BookEntity entity) {
        return Book.rehydrate(
                new DomainId<>(entity.id()),
                entity.isbn(),
                entity.title(),
                entity.year(),
                entity.language(),
                entity.totalCopies(),
                entity.availableCopies(),
                entity.status()
        );
    }

}
