package com.kovalenko.diploma.controller;

import com.kovalenko.diploma.mapper.LifeAspectMapper;
import com.kovalenko.diploma.service.AspectService;
import com.kovalenko.diploma.dto.LifeAspectDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/aspects")
public class AspectController {
    private final AspectService aspectService;
    private final LifeAspectMapper aspectMapper;

    public AspectController(AspectService aspectService, LifeAspectMapper aspectMapper) {
        this.aspectService = aspectService;
        this.aspectMapper = aspectMapper;
    }

    @GetMapping
    public List<LifeAspectDto> findAll() {
        return aspectMapper.mapToDtoList(aspectService.findAll());
    }
}
