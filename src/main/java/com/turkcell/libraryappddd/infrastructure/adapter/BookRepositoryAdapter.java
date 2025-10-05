package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.repository.BookRepository;
import com.turkcell.libraryappddd.infrastructure.entity.BookEntity;
import com.turkcell.libraryappddd.infrastructure.entity.LoanEntity;
import com.turkcell.libraryappddd.infrastructure.jparepository.BookJpaRepository;
import com.turkcell.libraryappddd.infrastructure.mapper.BookEntityMapper;
import com.turkcell.libraryappddd.infrastructure.mapper.LoanEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryAdapter implements BookRepository {
private final BookJpaRepository repository;
private final BookEntityMapper mapper;


    public BookRepositoryAdapter(BookJpaRepository repository, BookEntityMapper mapper, LoanEntityMapper loanEntityMapper) {
        this.repository = repository;
        this.mapper = mapper;

    }

    @Override
    public Book save(Book book) {
        BookEntity bookEntity = mapper.toEntity(book);


        bookEntity = repository.save(bookEntity);
        return mapper.toDomain(bookEntity);
    }

    @Override
    public Optional<Book> findById(DomainId<Book> bookId) {

        return repository.findById(bookId.value()).map(mapper::toDomain);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Book> findAllPaged(Integer pageIndex, Integer pageSize) {

        return repository
                .findAll(PageRequest.of(pageIndex,pageSize))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(DomainId<Book> bookId) {
        repository.deleteById(bookId.value());

    }
}
