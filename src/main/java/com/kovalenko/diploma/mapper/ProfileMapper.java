package com.kovalenko.diploma.mapper;

import com.kovalenko.diploma.dto.ProfileDto;
import com.kovalenko.diploma.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class ProfileMapper {
    @Autowired
    LifeAspectMapper lifeAspectMapper;

    @Autowired
    ReviewMapper reviewMapper;

    @Mapping(target = "user.contact.phone", source = "phone")
    @Mapping(target = "user.contact.email", source = "email")
    @Mapping(target = "user.fullName", source = "fullName")
    @Mapping(target = "user.avatar.id", source = "avatarId")
    @Mapping(target = "lifeAspects", expression = "java(lifeAspectMapper.mapToSetLifeAspects(profileDto.getLifeAspects()))")
    @Mapping(target = "reviews", expression = "java(reviewMapper.mapToReviews(profileDto.getReviews()))")
    public abstract Profile map(ProfileDto profileDto);

    @Mapping(target = "phone", source = "user.contact.phone")
    @Mapping(target = "email", source = "user.contact.email")
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "avatarId", source = "user.avatar.id")
    @Mapping(target = "lifeAspects", expression = "java(lifeAspectMapper.mapToSetStrings(profile.getLifeAspects()))")
    @Mapping(target = "reviews", expression = "java(reviewMapper.mapToReviewDtoList(profile.getReviews()))")
    public abstract ProfileDto map(Profile profile);


    List<ProfileDto> map(List<Profile> profiles) {
        return profiles.stream().map(this::map).collect(Collectors.toList());
    }
}
