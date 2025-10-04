package com.turkcell.libraryappddd.application.fine.query;

import com.turkcell.libraryappddd.application.fine.dto.FineResponse;
import com.turkcell.libraryappddd.core.cqrs.Query;
import jakarta.validation.constraints.Min;

import java.util.List;

public record ListFinesQuery(
        @Min(0) Integer pageIndex,
        @Min(1) Integer pageSize
) implements Query<List<FineResponse>> { }