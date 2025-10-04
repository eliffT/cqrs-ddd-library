package com.turkcell.libraryappddd.domain.model.book;


import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.author.Author;
import com.turkcell.libraryappddd.domain.model.book.enumStatus.BookStatus;
import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import com.turkcell.libraryappddd.domain.model.user.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Book {
    private final DomainId<Book> id;
    private final DomainId<Author> authorId;
    private final DomainId<Publisher> publisherId;
    private final DomainId<Category> categoryId;

    private final Isbn isbn;
    private String title;
    private Integer year;
    private String language;
    private Integer totalCopies;
    private Integer availableCopies;
    private BookStatus status;
    private BigDecimal price;

    private final List<Loan> loans = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();


    private Book(DomainId<Book> id, Isbn isbn, String title, Integer year, String language, Integer totalCopies,
                 Integer availableCopies, BookStatus status, DomainId<Author> authorId,
                 DomainId<Publisher> publisherId, DomainId<Category> categoryId, BigDecimal price) {
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
        this.price = price;
    }

    public static Book create(String title, Integer year, String language, Integer totalCopies, DomainId<Author> authorId,
                              DomainId<Publisher> publisherId, DomainId<Category> categoryId, BigDecimal price) {
        validateTitle(title);
        checkYear(year);
        validateLanguage(language);
        checkAmount(totalCopies);
        checkPrice(price);
        Isbn isbn = Isbn.generate();

        return new Book(DomainId.generate(), isbn,  title, year, language,
                        totalCopies, totalCopies, BookStatus.ACTIVE,
                        authorId, publisherId, categoryId, price);
    }

    public static Book rehydrate(DomainId<Book> id, Isbn isbn, String title, Integer year, String language,
                                 Integer totalCopies, Integer availableCopies,  BookStatus status, DomainId<Author> authorId,
                                 DomainId<Publisher> publisherId, DomainId<Category> categoryId,  BigDecimal price,
                                 List<Loan> loans, List<Reservation> reservations) {

        Book b = new Book(id, isbn, title, year, language, totalCopies, availableCopies,  status, authorId,
              publisherId, categoryId, price);
        if (loans != null) b.loans.addAll(loans);
        if (reservations != null) b.reservations.addAll(reservations);
        return b;
    }

    // Business Rules / Methods

    public Loan borrow(DomainId<User> userId, int loanDays) {
        if (availableCopies <= 0) throw new IllegalStateException("No copies available");
        // business rules: user might be validated externally (user status, fines, etc.)
        this.availableCopies--;
        if (this.availableCopies == 0) deactivate();
        ensureStockConsistency(this.totalCopies, this.availableCopies);

        Loan loan = Loan.create(userId, loanDays);
        loans.add(loan);
        return loan;
    }

    public void returnLoan(DomainId<Loan> loanId) {
        if (availableCopies >= totalCopies) throw new IllegalStateException("Available copies cannot exceed total copies");
        Loan loan = findLoan(loanId);
        loan.markAsReturned();
        this.availableCopies++;
        ensureStockConsistency(this.totalCopies, this.availableCopies);
        if (this.availableCopies > 0) activate();

    }
    public Reservation reserve(DomainId<User> userId, int validDays) {
        Reservation r = Reservation.create(userId, this.id,  validDays);
        reservations.add(r);
        return r;
    }

    public void cancelReservation(DomainId<Reservation> reservationId) {
        Reservation r = findReservation(reservationId);
        r.cancel();
    }

    // helpers
    private Loan findLoan(DomainId<Loan> loanId) {
        return loans.stream()
                .filter(l -> l.id().equals(loanId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Loan not found"));
    }

    private Reservation findReservation(DomainId<Reservation> reservationId) {
        return reservations.stream()
                .filter(r -> r.id().equals(reservationId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Reservation not found"));
    }

    public void restock(Integer quantityToRestock) {
        checkAmount(quantityToRestock);
        this.totalCopies += quantityToRestock;
        this.availableCopies += quantityToRestock;
        ensureStockConsistency(this.totalCopies, this.availableCopies);
    }

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

    public void resetAvailableCopies() {
        this.availableCopies = this.totalCopies;
        this.status = this.availableCopies > 0 ? BookStatus.ACTIVE : BookStatus.INACTIVE;
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

    private static void checkPrice(BigDecimal price) {
        if (price == null) throw new IllegalArgumentException("Price cannot be null");
        if (price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Price cannot be negative");
    }

    private static void validateTitle(String title){
        if(title == null || title.isEmpty())
            throw new IllegalArgumentException("Title cannot be null or empty");
        if(title.length() >= 255)
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


    // Getters
    public Isbn isbn() {
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
    public BigDecimal price() {
        return price;
    }
    public List<Loan> loans() { return Collections.unmodifiableList(loans); }
    public List<Reservation> reservations() { return Collections.unmodifiableList(reservations); }

}
