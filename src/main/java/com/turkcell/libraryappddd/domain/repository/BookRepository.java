package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.book.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(BookId bookId);
    List<Book> findAll();
    List<Book> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(BookId bookId);
}
