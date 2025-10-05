package com.turkcell.libraryappddd.application.user.command;

import com.turkcell.libraryappddd.application.user.dto.CreatedUserResponse;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, CreatedUserResponse> {

    @Override
    public CreatedUserResponse handle(CreateUserCommand command) {
        return null;
    }
}
