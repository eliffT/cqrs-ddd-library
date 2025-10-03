package com.turkcell.libraryappddd.infrastructure.mapper;
import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.fine.Fine;
import com.turkcell.libraryappddd.infrastructure.entity.FineEntity;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class FineEntityMapper {
    public FineEntity toEntity(Fine fine) {
        FineEntity FineEntity = new FineEntity();
        FineEntity.setId(fine.getId().value());
        FineEntity.setPaid(fine.getPaid());
        FineEntity.setAmount(fine.getAmaount().doubleValue());
        FineEntity.setPaymentDate(fine.getPaymentDate());
        return FineEntity;
    }

    public Fine toDomain(FineEntity entity) {
        return Fine.rehydrate(
                new DomainId<Fine>(entity.id()),
                entity.isPaid(),
                BigDecimal.valueOf(entity.amount()),
                entity.paymentDate()
        );
    }
}
