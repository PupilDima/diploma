package com.kovalenko.diploma.service.impl;

import com.kovalenko.diploma.service.AspectService;
import com.kovalenko.diploma.entity.LifeAspect;
import com.kovalenko.diploma.repository.AspectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AspectServiceImpl implements AspectService {
    private final AspectRepository aspectRepository;

    public AspectServiceImpl(AspectRepository aspectRepository) {
        this.aspectRepository = aspectRepository;
    }

    @Override
    public List<LifeAspect> findAll() {
        return aspectRepository.findAll();
    }
}
