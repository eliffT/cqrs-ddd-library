package com.turkcell.libraryappddd.application.book.mapper;

import com.turkcell.libraryappddd.application.book.dto.BookResponse;
import com.turkcell.libraryappddd.domain.model.book.Book;
import org.springframework.stereotype.Component;

@Component
public class BookResponseMapper {

    public BookResponse toResponse(Book book){
        return new BookResponse(book.id().value(), book.authorId().value(), book.categoryId().value(),
                book.title(), book.year(), book.language(), book.totalCopies(),
                book.price());
    }



}
