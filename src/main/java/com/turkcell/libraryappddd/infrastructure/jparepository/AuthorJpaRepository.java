package com.turkcell.libraryappddd.infrastructure.jparepository;

import com.turkcell.libraryappddd.infrastructure.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, UUID> {
}
