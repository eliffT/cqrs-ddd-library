package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.infrastructure.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryEntityMapper {

    public CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.id().value());
        categoryEntity.setName(category.name());
        categoryEntity.setDescription(category.description());
        return categoryEntity;
    }

    public Category toDomain(CategoryEntity entity) {
        return Category.rehydrate(
                new DomainId<>(entity.id()),
                entity.name(),
                entity.description()
        );
    }
}
