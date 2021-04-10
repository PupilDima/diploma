package com.kovalenko.diploma.mapper;

import com.kovalenko.diploma.dto.LifeAspectDto;
import com.kovalenko.diploma.entity.LifeAspect;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public interface LifeAspectMapper {
    LifeAspect map(LifeAspectDto lifeAspectDto);

    LifeAspectDto map(LifeAspect lifeAspect);

    List<LifeAspectDto> mapToDtoList(List<LifeAspect> lifeAspects);

    default Set<String> mapToSetStrings(Set<LifeAspect> lifeAspects) {
        return lifeAspects.stream().map(LifeAspect::getName).collect(Collectors.toSet());
    }

    default Set<LifeAspect> mapToSetLifeAspects(Set<String> strings) {
        return strings.stream().map(LifeAspect::new).collect(Collectors.toSet());
    }
}
