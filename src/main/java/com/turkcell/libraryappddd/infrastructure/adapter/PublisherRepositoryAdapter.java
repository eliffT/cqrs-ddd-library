package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;
import com.turkcell.libraryappddd.domain.repository.PublisherRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PublisherRepositoryAdapter implements PublisherRepository {


    @Override
    public Publisher save(Publisher publisher) {
        return null;
    }

    @Override
    public Optional<Publisher> findById(DomainId<Publisher> publisherId) {
        return Optional.empty();
    }

    @Override
    public List<Publisher> findAll() {
        return List.of();
    }

    @Override
    public List<Publisher> findAllPaged(Integer pageIndex, Integer pageSize) {
        return List.of();
    }

    @Override
    public void delete(DomainId<Publisher> publisherId) {

    }
}
