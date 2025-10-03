package com.turkcell.libraryappddd.application.author.query;


import com.turkcell.libraryappddd.application.author.dto.AuthorResponse;
import com.turkcell.libraryappddd.core.cqrs.Query;
import jakarta.validation.constraints.Min;

import java.util.List;

public record ListAuthorsQuery(
        @Min(0) Integer pageIndex,
        @Min(1) Integer pageSize
) implements Query<List<AuthorResponse>> {

}
