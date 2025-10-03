package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import com.turkcell.libraryappddd.infrastructure.entity.PublisherEntity;
import org.springframework.stereotype.Component;

@Component
public class PublisherEntityMapper {

    public PublisherEntity toEntity(Publisher publisher) {
        PublisherEntity publisherEntity = new PublisherEntity();
        publisherEntity.setId(publisher.getId().value());
        publisherEntity.setName(publisher.getName());
        return publisherEntity;
    }

    public Publisher toDomain(PublisherEntity entity) {
        return Publisher.rehydrate(
                new DomainId<Publisher>(entity.id()),
                entity.name()
        );
    }
}
