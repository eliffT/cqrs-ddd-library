package com.turkcell.libraryappddd.domain.model.loan;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record LoanId(UUID value) implements Serializable {
    public LoanId{
        Objects.requireNonNull(value, "value is null");
    }

    public static LoanId generate(){
        return new LoanId(UUID.randomUUID());
    }
}
