package com.turkcell.libraryappddd.domain.model.book;


import com.turkcell.libraryappddd.domain.model.DomainId;

import java.util.UUID;

public class Book {
    private final DomainId<Book> id;
    private final String isbn;
    private String title;
    private Integer year;
    private String language;
    private Integer totalCopies;
    private Integer availableCopies;
    private BookStatus status;


    private Book(DomainId<Book> id, String isbn, String title, Integer year, String language, Integer totalCopies,
                 Integer availableCopies, BookStatus status) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.language = language;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.status = status;
    }

    public static Book create(String title, Integer year, String language, Integer totalCopies,
                              Integer availableCopies) {
        validateTitle(title);
        checkYear(year);
        validateLanguage(language);
        checkAmount(totalCopies);
        checkAmount(availableCopies);
        validateStockConsistency(totalCopies, availableCopies);
        String isbn = generateIsbn();

        return new Book(DomainId.generate(), isbn,  title, year, language, totalCopies, availableCopies, BookStatus.ACTIVE);
    }

    public static Book rehydrate(DomainId<Book> id, String isbn, String title, Integer year, String language,
                                 Integer totalCopies, Integer availableCopies, BookStatus status) {
        return new Book(id, isbn, title, year, language, totalCopies, availableCopies, status);
    }

    // Domain Setters
    public void rename(String title) {
        validateTitle(title);
        this.title = title;
    }

    public void changeYear(Integer year) {
        checkYear(year);
        this.year = year;
    }

    public void changeLanguage(String language) {
        validateLanguage(language);
        this.language = language;
    }

    public void activate(){
        status = BookStatus.ACTIVE;
    }

    public void deactivate(){
        status = BookStatus.INACTIVE;
    }

    public void restock(Integer quantityToRestock) {
        checkAmount(quantityToRestock);
        this.totalCopies += quantityToRestock;
        this.availableCopies += quantityToRestock;
        validateStockConsistency(this.totalCopies, this.availableCopies);
    }

    // Validation
    private static void checkYear(Integer year) {
        if (year == null) {
            throw new IllegalArgumentException("Year cannot be null");
        }
        int currentYear = java.time.Year.now().getValue();

        if (year < 1500) {
            throw new IllegalArgumentException("Year must be greater than 1500");
        }

        if (year > currentYear) {
            throw new IllegalArgumentException("Year cannot be in the future");
        }
    }

    private static void validateTitle(String title){
        if(title == null || title.isEmpty())
            throw new IllegalArgumentException("Title cannot be null or empty");
        if(title.length() > 255)
            throw new IllegalArgumentException("Title length must be less than 255 characters");
    }

    private static void validateLanguage(String language){
        if(language == null || language.isEmpty())
            throw new IllegalArgumentException("Language cannot be null or empty");
        if(language.length() < 2 || language.length() > 15)
            throw new IllegalArgumentException("Language length must be between 2 and 15 characters");
    }

    private static void checkAmount(Integer amount){
        if (amount == null || amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");
    }

    private static void validateStockConsistency(Integer totalCopies, Integer availableCopies) {
        if (availableCopies > totalCopies) {
            throw new IllegalArgumentException("Available copies cannot exceed total copies");
        }
    }

    private static String generateIsbn() {
        return "ISBN-" + UUID.randomUUID().toString().substring(0, 13);
    }

    // Getters
    public String isbn() {
        return isbn;}
    public DomainId<Book> id() {
        return id;
    }
    public String title() {
        return title;
    }
    public Integer year() {
        return year;
    }
    public String language() {
        return language;
    }
    public Integer totalCopies() {
        return totalCopies;
    }
    public Integer availableCopies() {
        return availableCopies;
    }
    public BookStatus status() {
        return status;
    }


}
