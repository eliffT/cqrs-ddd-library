package com.turkcell.libraryappddd.infrastructure.mapper;
import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.book.Fine;
import com.turkcell.libraryappddd.infrastructure.entity.FineEntity;
import com.turkcell.libraryappddd.infrastructure.entity.LoanEntity;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.UUID;

@Component
public class FineEntityMapper {

    // Domain -> Entity
    public FineEntity toEntity(Fine fine, LoanEntity loanEntity) {
        FineEntity entity = new FineEntity();
        entity.setId(UUID.randomUUID()); // VO olduğu için entity ID burada üretiliyor
        entity.setAmount(fine.amount());
        entity.setReason(fine.reason());
        entity.setCreatedAt(fine.createdAt());
        entity.setLoan(loanEntity);
        entity.setPaid(false);          // yeni oluşturulan fine default olarak ödenmemiş
        entity.setPaymentDate(null);    // ödenme tarihi yok
        return entity;
    }

    // Entity -> Domain
    public Fine toDomain(FineEntity entity) {
        return Fine.of(entity.amount(), entity.reason()); // gecikme miktarı entity üzerinden hesaplanabilir
    }
}
