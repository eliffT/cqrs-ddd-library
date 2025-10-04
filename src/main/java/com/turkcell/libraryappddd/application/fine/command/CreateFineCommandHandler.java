//package com.turkcell.libraryappddd.application.fine.command;
//
//import com.turkcell.libraryappddd.application.fine.dto.CreatedFineResponse;
//import com.turkcell.libraryappddd.application.fine.mapper.CreateFineMapper;
//import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
//import com.turkcell.libraryappddd.domain.model.fine.Fine;
//import com.turkcell.libraryappddd.domain.repository.FineRepository;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CreateFineCommandHandler implements CommandHandler<CreateFineCommand, CreatedFineResponse> {
//
//    private final FineRepository fineRepository;
//    private final CreateFineMapper createFineMapper;
//
//    public CreateFineCommandHandler(FineRepository fineRepository, CreateFineMapper createFineMapper) {
//        this.fineRepository = fineRepository;
//        this.createFineMapper = createFineMapper;
//    }
//
//    @Override
//    public CreatedFineResponse handle(CreateFineCommand command) {
//        Fine fine = createFineMapper.toDomain(command);
//        fine = fineRepository.save(fine);
//        return createFineMapper.toResponse(fine);
//    }
//}
