package com.turkcell.libraryappddd.domain.repository;

import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.domain.model.category.CategoryId;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(CategoryId categoryId);
    List<Category> findAll();
    void deleteById(CategoryId categoryId);

}
