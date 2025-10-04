package com.turkcell.libraryappddd.infrastructure.jparepository;

import com.turkcell.libraryappddd.infrastructure.entity.FineEntity;
import com.turkcell.libraryappddd.infrastructure.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FineJpaRepository extends JpaRepository<FineEntity, UUID> {
    List<FineEntity> findByLoan(LoanEntity loan);
    void deleteByLoan(LoanEntity loan);  // loan bazlı silme
}
