package com.turkcell.libraryappddd.infrastructure.jparepository;

import com.turkcell.libraryappddd.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookJpaRepository extends JpaRepository<BookEntity, UUID> {
}
