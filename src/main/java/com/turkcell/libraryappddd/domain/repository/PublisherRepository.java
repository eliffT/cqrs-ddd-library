package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.publisher.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {
    Publisher save(Publisher publisher);
    Optional<Publisher> findById(DomainId<Publisher> publisherId);
    List<Publisher> findAll();
    List<Publisher> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(DomainId<Publisher> publisherId);
}
