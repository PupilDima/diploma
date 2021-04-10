package com.kovalenko.diploma.mapper;

import com.kovalenko.diploma.dto.ImageDto;
import com.kovalenko.diploma.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target = "name", expression = "java(getImageName(image))")
    ImageDto map(Image image);

    @Mapping(target = "path", source = "name")
    Image map(ImageDto imageDto);

    default String getImageName(Image image) {
        return image.getPath().substring(image.getPath().lastIndexOf("/") + 1);
    }
}
