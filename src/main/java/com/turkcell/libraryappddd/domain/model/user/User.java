package com.turkcell.libraryappddd.domain.model.user;

import com.turkcell.libraryappddd.domain.model.DomainId;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private final DomainId<User> id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDate createdAt;
    private MembershipLevel membershipLevel;

    public User(DomainId<User> id, String fullName, String username, String password,
            String email, String phone, LocalDate createdAt, MembershipLevel membershipLevel) {

    this.id = id;
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.createdAt = createdAt != null ? createdAt : LocalDate.now();
    this.membershipLevel = membershipLevel;
}

    // User Create (factory)
    public static User create(String fullName, String username, String password, String email, String phone,
                              MembershipLevel membershipLevel) {

        validateFullName(fullName);
        validateUsername(username);
        validatePassword(password);
        validateEmail(email);
        validatePhone(phone);

        return new User(DomainId.generate(), fullName, username, password, email, phone, LocalDate.now(),
                membershipLevel != null ? membershipLevel : MembershipLevel.STANDARD);
    }

    // Rehydrate
    public static User rehydrate(DomainId<User> id, String fullName, String username, String password, String email,
                                 String phone, LocalDate createdAt, MembershipLevel membershipLevel) {

        return new User(id, fullName, username, password, email, phone, createdAt, membershipLevel);
    }

    // Business Rules / Methods
    public void changeMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = Objects.requireNonNull(membershipLevel, "Membership level cannot be null");
    }


    public void rename(String fullName) {
        validateFullName(fullName);
        this.fullName = fullName;
    }

    public void changeEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public void changePhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    // Validations
    private static void validateFullName(String fullName) {
        if (fullName == null || fullName.isEmpty())
            throw new IllegalArgumentException("Full name cannot be empty");
        if (fullName.length() > 255)
            throw new IllegalArgumentException("Full name cannot exceed 255 characters");
    }

    private static void validateUsername(String username) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        if (username.length() > 50)
            throw new IllegalArgumentException("Username cannot exceed 50 characters");
    }

    private static void validatePassword(String password) {
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be empty");
    }

    private static void validateEmail(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Email cannot be empty");
        if (!email.contains("@"))
            throw new IllegalArgumentException("Email format is invalid");
    }

    private static void validatePhone(String phone) {
        if (phone == null || phone.isEmpty())
            throw new IllegalArgumentException("Phone cannot be empty");
    }

    // Getters
    public DomainId<User> id() { return id; }
    public String fullName() { return fullName; }
    public String username() { return username; }
    public String password() { return password; }
    public String email() { return email; }
    public String phone() { return phone; }
    public LocalDate createdAt() { return createdAt; }
    public MembershipLevel membershipLevel() { return membershipLevel; }
    public int getLoanDays() {
            return this.membershipLevel.getLoanDays();
    }

}

