package com.turkcell.libraryappddd.domain.model.loan;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.user.User;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {

    private final DomainId<Loan> id;
    private final DomainId<Book> bookId;
    private final DomainId<User> userId;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;

    private Loan(DomainId<Loan> id, DomainId<Book> bookId, DomainId<User> userId, LocalDate borrowDate, LocalDate dueDate,
                 LoanStatus status) {
        this.id = id;
        this.bookId = Objects.requireNonNull(bookId, "bookId cannot be null");
        this.userId = Objects.requireNonNull(userId, "userId cannot be null");
        this.borrowDate = Objects.requireNonNull(borrowDate, "borrowDate cannot be null");
        this.dueDate = Objects.requireNonNull(dueDate, "dueDate cannot be null");
        this.status = Objects.requireNonNull(status, "status cannot be null");

    }

    public static Loan create(DomainId<Book> bookId, User user, LocalDate borrowDate){

        LocalDate dueDate = borrowDate.plusDays(user.membershipLevel().getLoanDays());
        return new Loan(DomainId.generate(), bookId, user.id(), borrowDate, dueDate, LoanStatus.BORROWED);
    }

    public static Loan rehydrate(DomainId<Loan> id,
                                 DomainId<Book> bookId,
                                 DomainId<User> userId,
                                 LocalDate borrowDate,
                                 LocalDate dueDate,
                                 LocalDate returnDate,
                                 LoanStatus status) {
        return new Loan(id, bookId, userId, borrowDate, dueDate, status).withReturnDate(returnDate);
    }

    private Loan withReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public void markAsReturned(LocalDate returnDate) {
        if (returnDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException("Return date cannot be before borrow date");
        }
        this.returnDate = returnDate;
        this.status = LoanStatus.RETURNED;
    }

    public void cancel() {
        this.status = LoanStatus.CANCELLED;
    }

    //Getters
    public DomainId<Loan> id() { return id; }
    public DomainId<Book> bookId() { return bookId; }
    public DomainId<User> userId() { return userId; }
    public LocalDate borrowDate() { return borrowDate; }
    public LocalDate dueDate() { return dueDate; }
    public LocalDate returnDate() { return returnDate; }
    public LoanStatus status() { return status; }




}
