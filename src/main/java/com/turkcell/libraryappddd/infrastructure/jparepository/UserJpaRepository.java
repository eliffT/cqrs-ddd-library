package com.turkcell.libraryappddd.infrastructure.jparepository;

import com.turkcell.libraryappddd.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
}
