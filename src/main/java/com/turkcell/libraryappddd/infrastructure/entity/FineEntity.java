package com.turkcell.libraryappddd.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fines")
public class FineEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

    @Column(name = "amount", nullable = false, precision = 18, scale = 2)
    private double amount;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @ManyToOne()
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double amount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate paymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LoanEntity loan() {
        return loan;
    }

    public void setLoan(LoanEntity loan) {
        this.loan = loan;
    }
}
