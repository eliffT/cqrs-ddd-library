package com.turkcell.libraryappddd.application.category.command;

import com.turkcell.libraryappddd.application.category.dto.CreatedCategoryResponse;
import com.turkcell.libraryappddd.application.category.mapper.CreateCategoryMapper;
import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
import com.turkcell.libraryappddd.domain.model.category.Category;
import com.turkcell.libraryappddd.domain.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateCategoryCommandHandler implements CommandHandler<CreateCategoryCommand, CreatedCategoryResponse> {

    private final CategoryRepository categoryRepository;
    private final CreateCategoryMapper createCategoryMapper;

    public CreateCategoryCommandHandler(CategoryRepository categoryRepository, CreateCategoryMapper createCategoryMapper) {
        this.categoryRepository = categoryRepository;
        this.createCategoryMapper = createCategoryMapper;
    }

    @Override
    public CreatedCategoryResponse handle(CreateCategoryCommand command) {
        Category category = createCategoryMapper.toDomain(command);
        category = categoryRepository.save(category);
        return createCategoryMapper.toResponse(category);
    }
}
