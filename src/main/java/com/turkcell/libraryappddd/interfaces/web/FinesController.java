//package com.turkcell.libraryappddd.interfaces.web;
//
//import com.turkcell.libraryappddd.application.fine.command.CreateFineCommand;
//import com.turkcell.libraryappddd.application.fine.dto.CreatedFineResponse;
//import com.turkcell.libraryappddd.application.fine.dto.FineResponse;
//import com.turkcell.libraryappddd.application.fine.query.ListFinesQuery;
//import com.turkcell.libraryappddd.core.cqrs.CommandHandler;
//import com.turkcell.libraryappddd.core.cqrs.QueryHandler;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/fines")
//@Validated
//public class FinesController {
//
//    private final QueryHandler<ListFinesQuery, List<FineResponse>> listFinesQueryHandler;
//    private final CommandHandler<CreateFineCommand, CreatedFineResponse> createFineCommandHandler;
//
//    public FinesController(QueryHandler<ListFinesQuery, List<FineResponse>> listFinesQueryHandler,
//                           CommandHandler<CreateFineCommand, CreatedFineResponse> createFineCommandHandler)
//    {
//        this.listFinesQueryHandler = listFinesQueryHandler;
//        this.createFineCommandHandler = createFineCommandHandler;
//    }
//
//    @GetMapping
//    public List<FineResponse> getFines(@Valid ListFinesQuery query) {
//        return listFinesQueryHandler.handle(query);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public CreatedFineResponse createFine(@Valid @RequestBody CreateFineCommand command) {
//        return createFineCommandHandler.handle(command);
//    }
//}
