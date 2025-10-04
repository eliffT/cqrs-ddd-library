package com.turkcell.libraryappddd.domain.model.fine;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.loan.Loan;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import com.turkcell.libraryappddd.domain.model.user.User;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Fine {

    private final DomainId<Fine> id;
    private Boolean isPaid;
    private BigDecimal amaount;
    private LocalDate paymentDate;

    private Fine(DomainId<Fine> id, Boolean isPaid,
                 BigDecimal amaount, LocalDate paymentDate) {
        this.id = id;
        this.isPaid = isPaid;
        this.amaount = amaount;
        this.paymentDate = paymentDate;
    }


    public static Fine create(Boolean isPaid, BigDecimal amount, LocalDate paymentDate)
    {
        validateAmount(amount);
        return new Fine(DomainId.generate(), isPaid,amount, paymentDate);
    }

    public static Fine rehydrate(DomainId<Fine> id, Boolean isPaid, BigDecimal amaount, LocalDate paymentDate)
    {
        return new Fine(id,isPaid,amaount,paymentDate);
    }

    private static void validateAmount(BigDecimal amount){
        if(amount == null){
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if(amount.signum() < 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public void payFine(){
        isPaid = true;
        paymentDate = LocalDate.now();
    }


    public DomainId<Fine> getId() {
        return id;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public BigDecimal getAmaount() {
        return amaount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}

