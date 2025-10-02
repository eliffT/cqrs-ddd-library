package com.turkcell.libraryappddd.domain.model.book;


import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.author.Author;
import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;

import java.util.UUID;

public class Book {
    private final DomainId<Book> id;
    private final DomainId<Author> authorId;
    private final DomainId<Publisher> publisherId;
    private final DomainId<Category> categoryId;

    private final String isbn;
    private String title;
    private Integer year;
    private String language;
    private Integer totalCopies;
    private Integer availableCopies;
    private BookStatus status;


    private Book(DomainId<Book> id, String isbn, String title, Integer year, String language, Integer totalCopies,
                 Integer availableCopies, BookStatus status, DomainId<Author> authorId,
                 DomainId<Publisher> publisherId, DomainId<Category> categoryId) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.language = language;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.status = status;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.categoryId = categoryId;
    }

    public static Book create(String title, Integer year, String language, Integer totalCopies,
                              DomainId<Author> authorId, DomainId<Publisher> publisherId, DomainId<Category> categoryId) {
        validateTitle(title);
        checkYear(year);
        validateLanguage(language);
        checkAmount(totalCopies);
        String isbn = generateIsbn();
        validateIsbn(isbn);

        return new Book(DomainId.generate(), isbn,  title, year, language,
                        totalCopies, totalCopies, BookStatus.ACTIVE,
                        authorId, publisherId, categoryId);
    }

    public static Book rehydrate(DomainId<Book> id, String isbn, String title, Integer year, String language,
                                 Integer totalCopies, Integer availableCopies,  BookStatus status, DomainId<Author> authorId,
                                 DomainId<Publisher> publisherId, DomainId<Category> categoryId) {

        return new Book(id, isbn, title, year, language,
                        totalCopies, availableCopies,  status,
                        authorId, publisherId, categoryId);
    }

    // Business Rules / Methods
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
        ensureStockConsistency(this.totalCopies, this.availableCopies);
    }

    public void resetAvailableCopies() {
        this.availableCopies = this.totalCopies;
        this.status = this.availableCopies > 0 ? BookStatus.ACTIVE : BookStatus.INACTIVE;
    }

    public void borrow() {
        if (availableCopies <= 0) throw new IllegalStateException("No copies available to borrow");
        this.availableCopies--;
        if (this.availableCopies == 0) this.status = BookStatus.INACTIVE;
    }

    public void returnBook() {
        if (availableCopies >= totalCopies)
            throw new IllegalStateException("Available copies cannot exceed total copies");
        this.availableCopies++;
        this.status = BookStatus.ACTIVE;
    }

    private static String generateIsbn() {
        return "ISBN-" + UUID.randomUUID().toString().substring(0, 13);
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

    private static void ensureStockConsistency(Integer totalCopies, Integer availableCopies) {
        if (availableCopies > totalCopies) {
            throw new IllegalArgumentException("Available copies cannot exceed total copies");
        }
    }

    private static void validateIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) throw new IllegalArgumentException("ISBN cannot be empty");
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
    public DomainId<Author> authorId() { return authorId; }
    public DomainId<Publisher> publisherId() { return publisherId; }
    public DomainId<Category> categoryId() { return categoryId; }


}
