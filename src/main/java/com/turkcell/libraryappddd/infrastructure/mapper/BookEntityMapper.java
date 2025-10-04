package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.author.Author;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.book.Isbn;
import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import com.turkcell.libraryappddd.infrastructure.entity.BookEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookEntityMapper {
    public BookEntity toEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.id().value());
        bookEntity.setIsbn(book.isbn().value());
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
                new DomainId<Book>(entity.id()),
                new Isbn(entity.isbn()),
                entity.title(),
                entity.year(),
                entity.language(),
                entity.totalCopies(),
                entity.availableCopies(),
                entity.status(),
                new DomainId<Author>(entity.author().id()),
                new DomainId<Publisher>(entity.publisher().id()),
                new DomainId<Category>(entity.category().id()),
                entity.price()
        );
    }

}
