package com.turkcell.libraryappddd.application.category.query;

import com.turkcell.libraryappddd.application.category.dto.CategoryResponse;
import com.turkcell.libraryappddd.application.category.mapper.CategoryResponseMapper;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import com.turkcell.libraryappddd.domain.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListCategoriesQueryHandler implements QueryHandler<ListCategoriesQuery, List<CategoryResponse>> {

    private final CategoryRepository categoryRepository;
    private final CategoryResponseMapper categoryResponseMapper;

    public ListCategoriesQueryHandler(CategoryRepository categoryRepository, CategoryResponseMapper categoryResponseMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryResponseMapper = categoryResponseMapper;
    }

    @Override
    public List<CategoryResponse> handle(ListCategoriesQuery query) {
        return categoryRepository
                .findAllPaged(query.pageIndex(), query.pageSize())
                .stream()
                .map(categoryResponseMapper::toResponse)
                .toList();
    }
}
