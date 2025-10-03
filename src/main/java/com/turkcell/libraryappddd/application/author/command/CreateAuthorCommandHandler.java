package com.turkcell.libraryappddd.application.author.command;

import com.turkcell.libraryappddd.application.author.dto.CreatedAuthorResponse;
import com.turkcell.libraryappddd.application.author.mapper.CreateAuthorMapper;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.author.Author;
import com.turkcell.libraryappddd.domain.repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorCommandHandler implements CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> {
    private final AuthorRepository authorRepository;
    private final CreateAuthorMapper createAuthorMapper;

    public CreateAuthorCommandHandler(AuthorRepository authorRepository, CreateAuthorMapper createAuthorMapper) {
        this.authorRepository = authorRepository;
        this.createAuthorMapper = createAuthorMapper;
    }

    @Override
    public CreatedAuthorResponse handle(CreateAuthorCommand command) {
       Author author = createAuthorMapper.toDomain(command);
       author = authorRepository.save(author);
       return createAuthorMapper.toResponse(author);
    }
}
