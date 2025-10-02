package com.turkcell.libraryappddd.infrastructure.entity;

import com.turkcell.libraryappddd.domain.model.user.MembershipLevel;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "username",  nullable = false, length = 255)
    private String username;

    @Column(name = "password", nullable = false,  length = 50)
    private String password;

    @Column(unique = true, nullable = false, length = 65)    // unique email
    private String email;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @CreationTimestamp      // Otomatik tarih ataması yapılır.
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private MembershipLevel membershipLevel = MembershipLevel.STANDARD;

    @OneToMany(mappedBy = "user")
    private List<ReservationEntity> bookReservations;

    @OneToMany(mappedBy = "user")
    private List<LoanEntity> loans;

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String fullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String username() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String phone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate createdAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public MembershipLevel membershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public List<ReservationEntity> bookReservations() {
        return bookReservations;
    }

    public void setBookReservations(List<ReservationEntity> bookReservations) {
        this.bookReservations = bookReservations;
    }

    public List<LoanEntity> loans() {
        return loans;
    }

    public void setLoans(List<LoanEntity> loans) {
        this.loans = loans;
    }
}


