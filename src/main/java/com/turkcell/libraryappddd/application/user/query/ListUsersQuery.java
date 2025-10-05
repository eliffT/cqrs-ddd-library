package com.turkcell.libraryappddd.application.user.query;

import com.turkcell.libraryappddd.application.user.dto.UserResponse;
import com.turkcell.libraryappddd.core.cqrs.Query;

import java.util.List;

public record ListUsersQuery() implements Query<List<UserResponse>> {
}
