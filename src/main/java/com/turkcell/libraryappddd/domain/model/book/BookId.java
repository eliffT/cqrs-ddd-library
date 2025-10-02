package com.turkcell.libraryappddd.domain.model.book;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record BookId(UUID value) implements Serializable {
    public BookId{
        Objects.requireNonNull(value, "value is null");
    }

    public  static BookId generate(){
        return new BookId(UUID.randomUUID());
    }

}
