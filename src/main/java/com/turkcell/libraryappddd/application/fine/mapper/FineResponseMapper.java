package com.turkcell.libraryappddd.application.fine.mapper;

import com.turkcell.libraryappddd.application.fine.dto.FineResponse;
import com.turkcell.libraryappddd.domain.model.fine.Fine;
import org.springframework.stereotype.Component;

@Component
public class FineResponseMapper {
    public FineResponse toResponse(Fine fine) {
        return new FineResponse(
                fine.getId().value(),
                fine.getPaid(),
                fine.getAmaount(),
                fine.getPaymentDate()
        );
    }
}
