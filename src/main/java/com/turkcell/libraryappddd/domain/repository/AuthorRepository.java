package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.author.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(AuthorId authorId);
    List<Author> findAll();
    List<Author> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(AuthorId authorId);
}
