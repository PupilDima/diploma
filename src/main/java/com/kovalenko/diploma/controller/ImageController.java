package com.kovalenko.diploma.controller;

import com.kovalenko.diploma.dto.ImageDto;
import com.kovalenko.diploma.mapper.ImageMapper;
import com.kovalenko.diploma.service.ImageService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/images")
@Api(value = "Images management system")
public class ImageController {
    private final ImageService imageService;
    private final ImageMapper imageMapper;

    public ImageController(ImageService imageService, ImageMapper imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageById(@PathVariable Long id) {
        return imageService.getImageBytesById(id);
    }

    @PostMapping
    public ImageDto saveImage(@RequestParam("imageFile") MultipartFile imageFile) {
        return imageMapper.map(imageService.saveImage(imageFile));
    }

    @PutMapping("/{id}")
    public ImageDto updateImage(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable Long id) {
        return imageMapper.map(imageService.updateImage(imageFile, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImageById(id);

        return ResponseEntity.noContent().build();
    }
}
