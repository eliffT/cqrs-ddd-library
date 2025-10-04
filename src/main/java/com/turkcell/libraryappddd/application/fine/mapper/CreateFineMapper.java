package com.turkcell.libraryappddd.application.fine.mapper;

import com.turkcell.libraryappddd.application.fine.command.CreateFineCommand;
import com.turkcell.libraryappddd.application.fine.dto.CreatedFineResponse;
import com.turkcell.libraryappddd.domain.model.fine.Fine;
import org.springframework.stereotype.Component;

@Component
public class CreateFineMapper {

    public Fine toDomain(CreateFineCommand command) {
        return Fine.create(
                command.isPaid(),
                command.amount(),
                command.paymentDate()
        );
    }

    public CreatedFineResponse toResponse(Fine fine) {
        return new CreatedFineResponse(
                fine.getId().value(),
                fine.getPaid()
        );
    }
}
