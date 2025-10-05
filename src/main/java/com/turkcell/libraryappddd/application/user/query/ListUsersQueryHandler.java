package com.turkcell.libraryappddd.application.user.query;

import com.turkcell.libraryappddd.application.user.dto.UserResponse;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListUsersQueryHandler implements QueryHandler<ListUsersQuery, List<UserResponse>> {


    @Override
    public List<UserResponse> handle(ListUsersQuery query) {
        return List.of();
    }
}
