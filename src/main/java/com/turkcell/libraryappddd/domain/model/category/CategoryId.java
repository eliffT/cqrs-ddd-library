package com.turkcell.libraryappddd.domain.model.category;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record CategoryId(UUID value) implements Serializable {

    public CategoryId {
        Objects.requireNonNull(value, "value cannot be null");
    }

    public static CategoryId generate() {
        return new CategoryId(UUID.randomUUID());
    }
}


