package com.turkcell.libraryappddd.application.author.command;


import com.turkcell.libraryappddd.application.author.dto.CreatedAuthorResponse;
import com.turkcell.libraryappddd.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAuthorCommand(
        @NotBlank @Size(min=6,max=255) String fullName)
        implements Command<CreatedAuthorResponse> {

}
