package com.kovalenko.diploma.mapper;

import com.kovalenko.diploma.dto.ReviewDto;
import com.kovalenko.diploma.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public abstract class ReviewMapper {
    @Autowired
    ImageMapper imageMapper;

    @Mapping(target = "authorFullName", source = "review.author.fullName")
    @Mapping(target = "authorAvatar", expression = "java(imageMapper.map(review.getAuthor().getAvatar()))")
    @Mapping(target = "authorId", source = "review.author.id")
    public abstract ReviewDto map(Review review);

    @Mapping(target = "author.fullName", source = "authorFullName")
    @Mapping(target = "author.avatar", expression = "java(imageMapper.map(reviewDto.getAuthorAvatar()))")
    @Mapping(target = "author.id", source = "authorId")
    public abstract Review map(ReviewDto reviewDto);

    List<ReviewDto> mapToReviewDtoList(List<Review> reviews) {
        return reviews.stream().map(this::map).collect(Collectors.toList());
    }

    List<Review> mapToReviews(List<ReviewDto> reviewDtoList) {
        return reviewDtoList.stream().map(this::map).collect(Collectors.toList());
    }
}
