package com.turkcell.libraryappddd.application.author.mapper;

import com.turkcell.libraryappddd.application.author.command.CreateAuthorCommand;
import com.turkcell.libraryappddd.application.author.dto.CreatedAuthorResponse;
import com.turkcell.libraryappddd.domain.model.author.Author;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorMapper {
    public Author toDomain(CreateAuthorCommand command){
        return Author.create(command.fullName());

    }

    public CreatedAuthorResponse toResponse(Author author){
        return new CreatedAuthorResponse(author.id().value(), author.fullName());
    }
}
