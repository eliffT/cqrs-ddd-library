package com.turkcell.libraryappddd.application.user.dto;

import com.turkcell.libraryappddd.domain.model.user.MembershipLevel;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(UUID id, String fullName, String username, String email,
                           String phone, LocalDate createdAt,
                           MembershipLevel membershipLevel ) {

}
