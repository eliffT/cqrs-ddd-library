package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.fine.Fine;
import com.turkcell.libraryappddd.domain.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(DomainId<User> userId);
    List<User> findAll();
    List<User> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(DomainId<User> userId);
}
