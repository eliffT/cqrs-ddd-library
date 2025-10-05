package com.turkcell.libraryappddd.application.user.mapper;

import com.turkcell.libraryappddd.application.user.dto.UserResponse;
import com.turkcell.libraryappddd.domain.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {
    public UserResponse toResponse(User user){
        return new UserResponse(user.id().value(), user.fullName(), user.username(), user.email(),
                user.phone(),user.createdAt(),user.membershipLevel());
    }
}
