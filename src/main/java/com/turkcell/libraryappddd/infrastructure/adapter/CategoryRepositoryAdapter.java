package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.domain.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepository {


    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Optional<Category> findById(DomainId<Category> categoryId) {
        return Optional.empty();
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public List<Category> findAllPaged(Integer pageIndex, Integer pageSize) {
        return List.of();
    }

    @Override
    public void deleteById(DomainId<Category> categoryId) {

    }
}
