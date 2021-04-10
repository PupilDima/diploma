package com.kovalenko.diploma.repository;

import com.kovalenko.diploma.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
