package com.turkcell.libraryappddd.application.user.query;

import com.turkcell.libraryappddd.application.user.dto.UserResponse;
import com.turkcell.libraryappddd.core.cqrs.Query;
import jakarta.validation.constraints.Min;

import java.util.List;

public record ListUsersQuery(@Min(0) Integer pageIndex,
                             @Min(1) Integer pageSize
) implements Query<List<UserResponse>> {

}
