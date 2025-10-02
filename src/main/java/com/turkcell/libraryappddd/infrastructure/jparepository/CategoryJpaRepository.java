package com.turkcell.libraryappddd.infrastructure.jparepository;

import com.turkcell.libraryappddd.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {
}
