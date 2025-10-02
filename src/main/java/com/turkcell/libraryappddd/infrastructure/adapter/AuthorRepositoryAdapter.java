package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.author.Author;
import com.turkcell.libraryappddd.domain.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryAdapter implements AuthorRepository {


    @Override
    public Author save(Author author) {
        return null;
    }

    @Override
    public Optional<Author> findById(DomainId<Author> authorId) {
        return Optional.empty();
    }

    @Override
    public List<Author> findAll() {
        return List.of();
    }

    @Override
    public List<Author> findAllPaged(Integer pageIndex, Integer pageSize) {
        return List.of();
    }

    @Override
    public void delete(DomainId<Author> authorId) {

    }
}
