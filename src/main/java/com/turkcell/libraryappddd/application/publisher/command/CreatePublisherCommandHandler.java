package com.turkcell.libraryappddd.application.publisher.command;

import com.turkcell.libraryappddd.application.publisher.dto.CreatedPublisherResponse;
import com.turkcell.libraryappddd.application.publisher.mapper.CreatePublisherMapper;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import com.turkcell.libraryappddd.domain.repository.PublisherRepository;
import org.springframework.stereotype.Component;

@Component
public class CreatePublisherCommandHandler implements CommandHandler<CreatePublisherCommand, CreatedPublisherResponse> {

    private final PublisherRepository publisherRepository;
    private final CreatePublisherMapper createPublisherMapper;

    public CreatePublisherCommandHandler(PublisherRepository publisherRepository, CreatePublisherMapper createPublisherMapper) {
        this.publisherRepository = publisherRepository;
        this.createPublisherMapper = createPublisherMapper;
    }

    @Override
    public CreatedPublisherResponse handle(CreatePublisherCommand command) {
        Publisher publisher = createPublisherMapper.toDomain(command);
        publisher = publisherRepository.save(publisher);
        return createPublisherMapper.toResponse(publisher);
    }
}
