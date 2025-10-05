package com.turkcell.libraryappddd.application.loan.query;

import com.turkcell.libraryappddd.application.loan.dto.LoanResponse;
import com.turkcell.libraryappddd.core.cqrs.Query;
import jakarta.validation.constraints.Min;

import java.util.List;

public record ListLoansQuery(
        @Min(0) Integer pageIndex,
        @Min(1) Integer pageSize
) implements Query<List<LoanResponse>> { }
