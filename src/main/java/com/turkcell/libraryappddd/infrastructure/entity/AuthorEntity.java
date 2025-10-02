package com.turkcell.libraryappddd.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "full_name",  nullable = false, length = 255)
    private String fullName;

    @OneToMany(mappedBy = "author")
    private List<BookEntity> books;

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String fullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<BookEntity> books() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
