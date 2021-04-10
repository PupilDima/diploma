package com.kovalenko.diploma.service;

import com.kovalenko.diploma.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    byte[] getImageBytesById(Long imageId);

    Image saveImage(MultipartFile imageFile);

    Image updateImage(MultipartFile imageFile, Long id);

    void deleteImageById(Long id);
}
