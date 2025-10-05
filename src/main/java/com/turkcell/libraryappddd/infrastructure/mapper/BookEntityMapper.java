package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.author.Author;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.book.Isbn;
import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import com.turkcell.libraryappddd.infrastructure.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BookEntityMapper {
    private final LoanEntityMapper loanEntityMapper;
    private final ReservationEntityMapper reservationEntityMapper;

    public BookEntityMapper(LoanEntityMapper loanEntityMapper, ReservationEntityMapper reservationEntityMapper) {
        this.loanEntityMapper = loanEntityMapper;
        this.reservationEntityMapper = reservationEntityMapper;
    }

    public BookEntity toEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.id().value());

        bookEntity.setPrice(book.price());
        bookEntity.setIsbn(book.isbn().value());
        bookEntity.setTitle(book.title());
        bookEntity.setYear(book.year());
        bookEntity.setLanguage(book.language());
        bookEntity.setTotalCopies(book.totalCopies());
        bookEntity.setAvailableCopies(book.availableCopies());
        bookEntity.setStatus(book.status());
        if (book.authorId() != null) {
            bookEntity.setAuthor(new AuthorEntity(book.authorId().value()));
        }
        if (book.publisherId() != null) {
            bookEntity.setPublisher(new PublisherEntity(book.publisherId().value()));
        }
        if (book.categoryId() != null) {
            bookEntity.setCategory(new CategoryEntity(book.categoryId().value()));
        }

        // Loan mapping
        List<LoanEntity> loanEntities = book.loans().stream()
                .map(loan -> loanEntityMapper.toEntity(loan, bookEntity))
                .toList();
        bookEntity.setLoans(loanEntities);

        // Reservation mapping benzer şekilde
        List<ReservationEntity> reservationEntities = book.reservations().stream()
                .map(res -> reservationEntityMapper.toEntity(res, bookEntity))
                .toList();
        bookEntity.setReservations(reservationEntities);


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
                entity.price(),
                entity.loans() != null
                        ? entity.loans().stream().map(loanEntityMapper::toDomain).toList()
                        : List.of(),
                entity.reservations() != null
                        ? entity.reservations().stream().map(reservationEntityMapper::toDomain).toList()
                        : List.of()
        );
    }

}
