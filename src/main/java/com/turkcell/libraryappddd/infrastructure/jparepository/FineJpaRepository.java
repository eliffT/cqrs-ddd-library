package com.turkcell.libraryappddd.infrastructure.jparepository;

import com.turkcell.libraryappddd.infrastructure.entity.FineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FineJpaRepository extends JpaRepository<FineEntity, UUID> {
}
