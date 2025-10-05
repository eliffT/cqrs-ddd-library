package com.turkcell.libraryappddd.application.user.command;

import com.turkcell.libraryappddd.application.user.dto.CreatedUserResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;

public record CreateUserCommand() implements Command<CreatedUserResponse> {
}
