package com.turkcell.libraryappddd.application.fine.query;

import com.turkcell.libraryappddd.application.fine.dto.FineResponse;
import com.turkcell.libraryappddd.application.fine.mapper.FineResponseMapper;
import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
import com.turkcell.libraryappddd.domain.repository.FineRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListFinesQueryHandler implements QueryHandler<ListFinesQuery, List<FineResponse>> {

    private final FineRepository fineRepository;
    private final FineResponseMapper fineResponseMapper;

    public ListFinesQueryHandler(FineRepository fineRepository, FineResponseMapper fineResponseMapper) {
        this.fineRepository = fineRepository;
        this.fineResponseMapper = fineResponseMapper;
    }

    @Override
    public List<FineResponse> handle(ListFinesQuery query) {
        return fineRepository
                .findAllPaged(query.pageIndex(), query.pageSize())
                .stream()
                .map(fineResponseMapper::toResponse)
                .toList();
    }
}
