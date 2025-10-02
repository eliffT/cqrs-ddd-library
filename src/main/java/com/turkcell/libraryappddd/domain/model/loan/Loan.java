package com.turkcell.libraryappddd.domain.model.loan;

import java.time.LocalDate;

public class Loan {
    private final LoanId id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private LoanStatus status;

    private Loan(LoanId id, LocalDate borrowDate) {
        this.id = id;
        this.borrowDate = borrowDate;
    }

    public static Loan create(LocalDate borrowDate){
        return new Loan(LoanId.generate(), borrowDate);
    }


//    public void setBorrowDate(LocalDate borrowDate) {
//        this.borrowDate = borrowDate;
//    }
//
//    public void setReturnDate(LocalDate returnDate) {
//        this.returnDate = returnDate;
//    }
//
//    public void calculateDueDate(LocalDate dueDate) {
//        this.dueDate = dueDate;
//    }
//
//    public void setStatus(LoanStatus status) {
//        this.status = status;
//    }


    public LoanId id() {
        return id;
    }
    public LocalDate borrowDate() {
        return borrowDate;
    }
    public LocalDate returnDate() {
        return returnDate;
    }
    public LocalDate dueDate() {
        return dueDate;
    }
    public LoanStatus status() {
        return status;
    }



}
