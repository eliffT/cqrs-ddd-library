package com.turkcell.libraryappddd.domain.model.book;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.enumStatus.LoanStatus;
import com.turkcell.libraryappddd.domain.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

public class Loan {

    private final DomainId<Loan> id;
    private final DomainId<User> userId;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;

    // Sabit günlük gecikme ücreti
    private static final BigDecimal DAILY_LATE_FEE = new BigDecimal("15");

    private Loan(DomainId<Loan> id, DomainId<User> userId, LocalDate borrowDate, LocalDate dueDate,
                 LoanStatus status) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId, "userId cannot be null");
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public static Loan create(DomainId<User> userId,  int loanDays){

        LocalDate borrowDate = LocalDate.now();
        if (loanDays <= 0) throw new IllegalArgumentException("Loan days must be greater than 0");
        LocalDate dueDate = borrowDate.plusDays(loanDays);

        return new Loan(DomainId.generate(), userId, borrowDate, dueDate, LoanStatus.BORROWED);
    }

    public static Loan rehydrate(DomainId<Loan> id,
                                 DomainId<User> userId,
                                 LocalDate borrowDate,
                                 LocalDate dueDate,
                                 LocalDate returnDate,
                                 LoanStatus status) {

        Loan loan = new Loan(id, userId, borrowDate, dueDate, status);
        if (status == LoanStatus.RETURNED && returnDate != null)
            loan.returnDate = returnDate;
        return loan;
    }


    public Optional<Fine> calculateLateFine() {
        LocalDate endDate = returnDate != null ? returnDate : LocalDate.now();
        if(endDate.isAfter(dueDate)) {
            int daysLate = (int) ChronoUnit.DAYS.between(dueDate, endDate);
            return Optional.of(Fine.forLate(daysLate, DAILY_LATE_FEE));
        }
        return Optional.empty(); // gecikme yok
    }

    private boolean isActive() {
        return status == LoanStatus.BORROWED || status == LoanStatus.LATE;
    }

    public void markAsReturned() {
        if(!isActive()) throw new IllegalStateException("Only borrowed or late loans can be returned");
        this.status = LoanStatus.RETURNED;
        this.returnDate = LocalDate.now();
    }

    // Ödünç alınan kitapların teslim tarihi kontrol edilir, gecikmiş ise LATE atanır
    public boolean checkAndMarkOverdue(LocalDate today) {
        if (status == LoanStatus.BORROWED && today.isAfter(dueDate)) {
            status = LoanStatus.LATE;
            return true;
        }
        return false; // Gecikme yoksa false
    }


    //Getters
    public DomainId<Loan> id() { return id; }
    public DomainId<User> userId() { return userId; }
    public LocalDate borrowDate() { return borrowDate; }
    public LocalDate dueDate() { return dueDate; }
    public LocalDate returnDate() { return returnDate; }
    public LoanStatus status() { return status; }

}
