package com.kovalenko.diploma.service.impl;

import com.kovalenko.diploma.entity.Review;
import com.kovalenko.diploma.repository.ReviewRepository;
import com.kovalenko.diploma.service.ReviewService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    @Transactional
    public Review save(Review review) {
        return reviewRepository.save(review);
    }
}
