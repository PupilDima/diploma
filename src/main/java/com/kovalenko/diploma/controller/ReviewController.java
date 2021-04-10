package com.kovalenko.diploma.controller;

import com.kovalenko.diploma.mapper.ReviewMapper;
import com.kovalenko.diploma.dto.ReviewDto;
import com.kovalenko.diploma.service.ReviewService;
import com.kovalenko.diploma.service.impl.ReviewServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewServiceImpl reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping()
    public ReviewDto save(@RequestBody ReviewDto reviewDto) {
        return reviewMapper.map(reviewService.save(reviewMapper.map(reviewDto)));
    }
}
