package com.turkcell.libraryappddd.infrastructure.adapter;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.domain.repository.CategoryRepository;
import com.turkcell.libraryappddd.infrastructure.entity.CategoryEntity;
import com.turkcell.libraryappddd.infrastructure.jparepository.CategoryJpaRepository;
import com.turkcell.libraryappddd.infrastructure.mapper.CategoryEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.lang.invoke.CallSite;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepository {
   private final CategoryJpaRepository repository;
   private final CategoryEntityMapper mapper;

    public CategoryRepositoryAdapter(CategoryJpaRepository repository, CategoryEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = mapper.toEntity(category);
        categoryEntity = repository.save(categoryEntity);
        return mapper.toDomain(categoryEntity);
    }

    @Override
    public Optional<Category> findById(DomainId<Category> categoryId) {
        return repository.findById(categoryId.value()).map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Category> findAllPaged(Integer pageIndex, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageIndex,pageSize))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(DomainId<Category> categoryId) {
        repository.deleteById(categoryId.value());
    }
}
