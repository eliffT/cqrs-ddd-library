package com.turkcell.libraryappddd.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fines")
public class FineEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

    @ManyToOne()
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;

    public UUID id() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal amount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String reason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate createdAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate paymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LoanEntity loan() {
        return loan;
    }
    public void setLoan(LoanEntity loan) {
        this.loan = loan;
    }
}
