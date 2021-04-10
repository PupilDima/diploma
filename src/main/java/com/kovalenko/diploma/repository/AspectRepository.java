package com.kovalenko.diploma.repository;

import com.kovalenko.diploma.entity.LifeAspect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AspectRepository extends JpaRepository<LifeAspect, Long> {
}
