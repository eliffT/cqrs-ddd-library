package com.turkcell.libraryappddd.interfaces.web;

import com.turkcell.libraryappddd.application.author.command.CreateAuthorCommand;
import com.turkcell.libraryappddd.application.author.dto.AuthorResponse;
import com.turkcell.libraryappddd.application.author.dto.CreatedAuthorResponse;
import com.turkcell.libraryappddd.application.author.query.ListAuthorsQuery;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@Validated
public class AuthorController {
    private final QueryHandler<ListAuthorsQuery, List<AuthorResponse>> listAuthorsQueryListQueryHandler;
    private final CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createAuthorComamndHandler;

    public AuthorController(QueryHandler<ListAuthorsQuery, List<AuthorResponse>> listAuthorsQueryListQueryHandler, CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createAuthorComamndHandler) {
        this.listAuthorsQueryListQueryHandler = listAuthorsQueryListQueryHandler;
        this.createAuthorComamndHandler = createAuthorComamndHandler;
    }

    @GetMapping
    public List<AuthorResponse> getAuthors(@Valid ListAuthorsQuery query)
    {
        return listAuthorsQueryListQueryHandler.handle(query);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAuthorResponse createAuthor(@Valid @RequestBody CreateAuthorCommand command){
        return createAuthorComamndHandler.handle(command);
    }
}
