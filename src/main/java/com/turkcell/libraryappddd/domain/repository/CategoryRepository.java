package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(DomainId<Category> categoryId);
    List<Category> findAll();
    List<Category> findAllPaged(Integer pageIndex, Integer pageSize);
    void deleteById(DomainId<Category> categoryId);

}
