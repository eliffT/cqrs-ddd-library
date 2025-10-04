package com.turkcell.libraryappddd.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkcell.libraryappddd.domain.model.book.enumStatus.LoanStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "loans")
public class LoanEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)  // Enum değerlerini String olarak kaydedilir
    private LoanStatus status;

    @ManyToOne()
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private BookEntity book;

    @OneToMany(mappedBy = "loan")
    private List<FineEntity> fineList;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate borrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate dueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate returnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus status() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public BookEntity book() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public List<FineEntity> fineList() {
        return fineList;
    }

    public void setFineList(List<FineEntity> fineList) {
        this.fineList = fineList;
    }

    public UserEntity user() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
