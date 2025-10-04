package com.turkcell.libraryappddd.application.publisher.mapper;

import com.turkcell.libraryappddd.application.publisher.dto.PublisherResponse;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherResponseMapper {

    public PublisherResponse toResponse(Publisher publisher) {
        return new PublisherResponse(
                publisher.getId().value(),
                publisher.getName()
        );
    }
}
