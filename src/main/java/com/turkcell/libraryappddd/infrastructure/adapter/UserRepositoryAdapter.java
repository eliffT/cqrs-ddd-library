package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.domain.repository.UserRepository;
import com.turkcell.libraryappddd.infrastructure.entity.UserEntity;
import com.turkcell.libraryappddd.infrastructure.jparepository.UserJpaRepository;
import com.turkcell.libraryappddd.infrastructure.mapper.UserEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository repository;
    private final UserEntityMapper mapper;

    public UserRepositoryAdapter(UserJpaRepository repository, UserEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.toEntity(user);
        userEntity = repository.save(userEntity);
        return mapper.toDomain(userEntity);

    }

    @Override
    public Optional<User> findById(DomainId<User> userId) {
        return repository.findById(userId.value()).map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<User> findAllPaged(Integer pageIndex, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageIndex, pageSize))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(DomainId<User> userId) {
        repository.deleteById(userId.value());
    }
}
