package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.book.Loan;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.infrastructure.entity.LoanEntity;
import com.turkcell.libraryappddd.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoanEntityMapper {
    public LoanEntity toEntity(Loan loan) {
        if (loan == null) return null;

        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setId(loan.id().value());
        loanEntity.setBorrowDate(loan.borrowDate());
        loanEntity.setDueDate(loan.dueDate());
        loanEntity.setReturnDate(loan.returnDate());
        loanEntity.setStatus(loan.status());

        if (loan.userId() != null)
            loanEntity.setUser(new UserEntity(loan.userId().value()));
        return loanEntity;
    }

    public Loan toDomain(LoanEntity entity) {
        if (entity == null) return null;
        if (entity.user() == null)
            throw new IllegalStateException("Cannot map Loan: user is null");

        return Loan.rehydrate(
                new DomainId<>(entity.id()),
                new DomainId<User>(entity.user().id()),
                entity.borrowDate(),
                entity.dueDate(),
                entity.returnDate(),
                entity.status());
    }
}
