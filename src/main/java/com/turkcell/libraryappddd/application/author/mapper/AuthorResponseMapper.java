package com.turkcell.libraryappddd.application.author.mapper;

import com.turkcell.libraryappddd.application.author.dto.AuthorResponse;
import com.turkcell.libraryappddd.domain.model.author.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorResponseMapper {
    public AuthorResponse toResponse(Author author){
        return  new AuthorResponse(author.id().value(), author.fullName());
    }
}
