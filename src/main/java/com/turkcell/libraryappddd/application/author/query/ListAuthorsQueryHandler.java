package com.turkcell.libraryappddd.application.author.query;

import com.turkcell.libraryappddd.application.author.dto.AuthorResponse;
import com.turkcell.libraryappddd.application.author.mapper.AuthorResponseMapper;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import com.turkcell.libraryappddd.domain.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAuthorsQueryHandler implements QueryHandler<ListAuthorsQuery,List<AuthorResponse>> {

    private final AuthorRepository authorRepository;
    private final AuthorResponseMapper authorResponseMapper;

    public ListAuthorsQueryHandler(AuthorRepository authorRepository, AuthorResponseMapper authorResponseMapper) {
        this.authorRepository = authorRepository;
        this.authorResponseMapper = authorResponseMapper;
    }

    @Override
    public List<AuthorResponse> handle(ListAuthorsQuery query) {
        return authorRepository.findAll()
                .stream()
                .map(authorResponseMapper::toResponse)
                .toList();
    }
}
