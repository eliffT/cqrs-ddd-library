package com.turkcell.libraryappddd.infrastructure.mapper;


import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.author.Author;
import com.turkcell.libraryappddd.infrastructure.entity.AuthorEntity;
import org.springframework.stereotype.Component;


@Component
public class AuthorEntityMapper {
    public AuthorEntity toEntity(Author author){
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(author.id().value());
        authorEntity.setFullName(author.fullName());
        return authorEntity;
    }

    public Author toDomain(AuthorEntity entity) {
        return Author.rehydrate(new DomainId<Author>(entity.id()), entity.fullName());
    }
}
