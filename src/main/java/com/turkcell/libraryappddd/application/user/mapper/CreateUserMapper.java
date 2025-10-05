package com.turkcell.libraryappddd.application.user.mapper;

import com.turkcell.libraryappddd.application.user.command.CreateUserCommand;
import com.turkcell.libraryappddd.application.user.dto.CreatedUserResponse;
import com.turkcell.libraryappddd.application.user.dto.UserResponse;
import com.turkcell.libraryappddd.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class CreateUserMapper {
    public User toDomain(CreateUserCommand command){
        return User.create(command.fullName(),command.username(),
                command.password(), command.email(), command.phone(), null);
    }

    public CreatedUserResponse toResponse(User user, String message){
        return new CreatedUserResponse(user.id().value(), user.fullName(),message );
    }
}
