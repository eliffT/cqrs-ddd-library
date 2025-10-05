package com.turkcell.libraryappddd.interfaces.web;

import com.turkcell.libraryappddd.application.user.command.CreateUserCommand;
import com.turkcell.libraryappddd.application.user.dto.CreatedUserResponse;
import com.turkcell.libraryappddd.application.user.dto.UserResponse;
import com.turkcell.libraryappddd.application.user.query.ListUsersQuery;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final QueryHandler<ListUsersQuery, List<UserResponse>> listUsersQueryListQueryHandler;
    private final CommandHandler<CreateUserCommand, CreatedUserResponse> createUserCommandHandler;

    public UserController(QueryHandler<ListUsersQuery, List<UserResponse>> listUsersQueryListQueryHandler,
                          CommandHandler<CreateUserCommand, CreatedUserResponse> createUserCommandHandler) {
        this.listUsersQueryListQueryHandler = listUsersQueryListQueryHandler;
        this.createUserCommandHandler = createUserCommandHandler;
    }

    @GetMapping
    public List<UserResponse> getUsers(@Valid ListUsersQuery query) {
        return listUsersQueryListQueryHandler.handle(query);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedUserResponse createUser(@Valid @RequestBody CreateUserCommand command) {
        return createUserCommandHandler.handle(command);
    }
}
