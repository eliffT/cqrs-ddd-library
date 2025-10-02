package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {


    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(DomainId<User> userId) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public List<User> findAllPaged(Integer pageIndex, Integer pageSize) {
        return List.of();
    }

    @Override
    public void delete(DomainId<User> userId) {

    }
}
