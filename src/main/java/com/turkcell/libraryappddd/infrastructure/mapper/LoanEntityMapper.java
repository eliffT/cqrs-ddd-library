package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.author.Author;
import com.turkcell.libraryappddd.domain.model.book.Book;
import com.turkcell.libraryappddd.domain.model.loan.Loan;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.infrastructure.entity.AuthorEntity;
import com.turkcell.libraryappddd.infrastructure.entity.LoanEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanEntityMapper {
    public LoanEntity toEntity(Loan loan)
    {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setId(loan.id().value());
        loanEntity.setBorrowDate(loan.borrowDate());
        loanEntity.setDueDate(loan.dueDate());
        loanEntity.setReturnDate(loan.returnDate());
        return loanEntity;
    }

    public Loan toDomain(LoanEntity entity) {
        return Loan.rehydrate(new DomainId<>(entity.id()),
                new DomainId<Book>(entity.book().id()),
                new DomainId<User>(entity.user().id()),
                entity.borrowDate(),
                entity.dueDate(),
                entity.returnDate(),
                entity.status());
    }
}
