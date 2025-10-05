package com.turkcell.libraryappddd.application.user.command;

import com.turkcell.libraryappddd.application.user.dto.CreatedUserResponse;
import com.turkcell.libraryappddd.application.user.mapper.CreateUserMapper;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, CreatedUserResponse> {
private final UserRepository userRepository;
private final CreateUserMapper createUserMapper;

    public CreateUserCommandHandler(UserRepository userRepository, CreateUserMapper createUserMapper) {
        this.userRepository = userRepository;
        this.createUserMapper = createUserMapper;
    }

    @Override
    public CreatedUserResponse handle(CreateUserCommand command) {
        User user = createUserMapper.toDomain(command);
        user = userRepository.save(user);
        return  createUserMapper.toResponse(user, "User created");
    }
}
