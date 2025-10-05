package com.turkcell.libraryappddd.application.user.command;

import com.turkcell.libraryappddd.application.user.dto.CreatedUserResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserCommand(
        @NotBlank @Size(max = 255) String fullName,
        @NotBlank @Size(max = 50) String username,
        @NotBlank @Size(min = 5, max = 50) String password,
        @NotBlank @Size(max = 255) String email,
        @NotBlank @Size(min = 11) String phone
) implements Command<CreatedUserResponse> {


}
