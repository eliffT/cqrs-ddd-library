package com.turkcell.libraryappddd.domain.model;

import java.util.Objects;
import java.util.UUID;

public record DomainId<T>(UUID value) {
    public DomainId {
        Objects.requireNonNull(value, "value is null");
    }

    public static <T> DomainId<T> generate() {
        return new DomainId<>(UUID.randomUUID());
    }
}
