package com.turkcell.libraryappddd.domain.model.book.enumStatus;

public enum LoanStatus {
    BORROWED,   // Kitap ödünç alınmış
    RETURNED,   // Kitap iade edilmiş
    LATE,        // Teslim tarihi geçmiş
    CANCELLED

}
