package com.turkcell.libraryappddd.infrastructure.mapper;

import com.turkcell.libraryappddd.domain.model.DomainId;
import com.turkcell.libraryappddd.domain.model.user.User;
import com.turkcell.libraryappddd.infrastructure.entity.UserEntity;

public class UserEntityMapper {

    public UserEntity toEntity(User user){
        if (user == null) return null;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.id().value());
        userEntity.setFullName(user.fullName());
        userEntity.setEmail(user.email());
        userEntity.setPassword(user.password());
        userEntity.setUsername(user.username());
        userEntity.setPhone(user.phone());
        userEntity.setMembershipLevel(user.membershipLevel());
        return userEntity;
    }

    public User toDomain(UserEntity entity){
        if (entity == null) return null;

        return User.rehydrate(
                new DomainId<User>(entity.id()),
                entity.fullName(), entity.username(), entity.password(), entity.email(),
                entity.phone(), entity.createdAt(), entity.membershipLevel()
        );
    }
}
