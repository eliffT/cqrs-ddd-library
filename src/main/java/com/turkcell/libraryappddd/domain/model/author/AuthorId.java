package com.turkcell.libraryappddd.domain.model.author;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record AuthorId(UUID value) implements Serializable{
    public AuthorId {
        Objects.requireNonNull(value);
    }

    public static AuthorId generate() {
        return new AuthorId(UUID.randomUUID());
    }

}
