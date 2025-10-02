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
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public static Loan create(DomainId<Book> bookId, DomainId<User> userId,  int loanDays){

        LocalDate borrowDate = LocalDate.now();
        if (loanDays <= 0) throw new IllegalArgumentException("Loan days must be greater than 0");
        LocalDate dueDate = borrowDate.plusDays(loanDays);

        return new Loan(DomainId.generate(), bookId, userId, borrowDate, dueDate, LoanStatus.BORROWED);
    }

    public static Loan rehydrate(DomainId<Loan> id,
                                 DomainId<Book> bookId,
                                 DomainId<User> userId,
                                 LocalDate borrowDate,
                                 LocalDate dueDate,
                                 LocalDate returnDate,
                                 LoanStatus status) {

        Loan loan = new Loan(id, bookId, userId, borrowDate, dueDate, status);
        if (status == LoanStatus.RETURNED && returnDate != null)
            loan.returnDate = returnDate;
        return loan;
    }


    private boolean isActive() {
        return status == LoanStatus.BORROWED || status == LoanStatus.LATE;
    }

    public void markAsReturned() {
        if(!isActive()) throw new IllegalStateException("Only borrowed or late loans can be returned");
        this.status = LoanStatus.RETURNED;
        this.returnDate = LocalDate.now();
    }

    public boolean checkAndMarkOverdue(LocalDate today) {
       // Ödünç alınan kitapların teslim tarihi kontrol edilir, gecikmiş ise LATE atanır
        if (status == LoanStatus.BORROWED && today.isAfter(dueDate)) {
            status = LoanStatus.LATE;
            return true;
        }
        return false; // Gecikme yoksa false
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
