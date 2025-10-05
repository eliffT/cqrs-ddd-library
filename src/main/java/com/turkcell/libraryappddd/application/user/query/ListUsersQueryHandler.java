package com.turkcell.libraryappddd.application.user.query;

import com.turkcell.libraryappddd.application.user.dto.UserResponse;
import com.turkcell.libraryappddd.application.user.mapper.UserResponseMapper;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import com.turkcell.libraryappddd.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListUsersQueryHandler implements QueryHandler<ListUsersQuery, List<UserResponse>> {
private final UserRepository userRepository;
private final UserResponseMapper userResponseMapper;

    public ListUsersQueryHandler(UserRepository userRepository, UserResponseMapper userResponseMapper) {
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
    }

    @Override
    public List<UserResponse> handle(ListUsersQuery query) {

        return userRepository.findAll().stream().map(userResponseMapper::toResponse).toList();
    }
}
