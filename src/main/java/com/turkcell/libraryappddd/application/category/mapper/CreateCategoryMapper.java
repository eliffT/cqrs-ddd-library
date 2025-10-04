package com.turkcell.libraryappddd.application.category.mapper;

import com.turkcell.libraryappddd.application.category.command.CreateCategoryCommand;
import com.turkcell.libraryappddd.application.category.dto.CreatedCategoryResponse;
import com.turkcell.libraryappddd.domain.model.category.Category;
import org.springframework.stereotype.Component;

@Component
public class CreateCategoryMapper {

    public Category toDomain(CreateCategoryCommand command) {
        return Category.create(
                command.name(),
                command.description()
        );
    }

    public CreatedCategoryResponse toResponse(Category category) {
        return new CreatedCategoryResponse(
                category.id().value(),
                category.name(),
                category.description()
        );
    }
}
