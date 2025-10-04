package com.turkcell.libraryappddd.application.publisher.mapper;

import com.turkcell.libraryappddd.application.publisher.command.CreatePublisherCommand;
import com.turkcell.libraryappddd.application.publisher.dto.CreatedPublisherResponse;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import org.springframework.stereotype.Component;

@Component
public class CreatePublisherMapper {

    public Publisher toDomain(CreatePublisherCommand command) {
        return Publisher.create(command.name());
    }

    public CreatedPublisherResponse toResponse(Publisher publisher) {
        return new CreatedPublisherResponse(
                publisher.getId().value(),
                publisher.getName()
        );
    }
}
