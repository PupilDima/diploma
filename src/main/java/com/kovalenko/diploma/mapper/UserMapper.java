package com.kovalenko.diploma.mapper;

import com.kovalenko.diploma.dto.UserDto;
import com.kovalenko.diploma.entity.Profile;
import com.kovalenko.diploma.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "email", source = "entity.contact.email")
    @Mapping(target = "avatarId", source = "entity.avatar.id")
    UserDto map(User entity);

    @Mapping(target = "contact.email", source = "email")
    @Mapping(target = "avatar.id", source = "avatarId")
    User map(UserDto entity);

    @Mapping(target = "email", source = "entity.contact.email")
    @Mapping(target = "avatarId", source = "entity.avatar.id")
    List<UserDto> map(List<User> entities);

    @Mapping(source = "entity.user.id", target = "id")
    @Mapping(source = "entity.user.fullName", target = "fullName")
    @Mapping(source = "entity.user.contact.email", target = "email")
    @Mapping(target = "avatarId", source = "entity.user.avatar.id")
    UserDto map(Profile entity);

    default List<UserDto> mapFromProfiles(List<Profile> entities) {
        return entities.stream().map(this::map).collect(Collectors.toList());
    }
}
