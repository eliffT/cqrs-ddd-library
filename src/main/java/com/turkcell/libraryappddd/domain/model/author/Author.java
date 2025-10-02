package com.turkcell.libraryappddd.domain.model.author;

public class Author {
    private final AuthorId id;
    private String firstName;
    private String lastName;

    private Author(AuthorId id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Author create(String firstName, String lastName) {
        validateFirstName(firstName);
        validateLastName(lastName);
        return new Author(AuthorId.generate(), firstName, lastName);
    }

    public static Author rehydrate(AuthorId id, String firstName, String lastName) {
        return new Author(id, firstName, lastName);
    }

    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public static void validateFirstName(String firstName){
        if(firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException("First name cannot be empty");
        if(firstName.length() >= 3 || firstName.length() < 255)
            throw new IllegalArgumentException("First name must be between 3 and 255 characters");
    }

    public static void validateLastName(String lastName){
        if(lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("Last name cannot be empty");
        if(lastName.length() >= 3 || lastName.length() < 255)
            throw new IllegalArgumentException("Last name must be between 3 and 255 characters");
    }

    public AuthorId id() {
        return id;
    }
    public String firstName() {
        return firstName;
    }
    public String lastName() {
        return lastName;
    }
}
