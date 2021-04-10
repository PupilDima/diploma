package com.kovalenko.diploma.service.impl;

import com.kovalenko.diploma.entity.Image;
import com.kovalenko.diploma.exception.RepositoryException;
import com.kovalenko.diploma.service.FolderService;
import com.kovalenko.diploma.service.ImageService;
import com.kovalenko.diploma.repository.ImageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger LOG = LogManager.getLogger(ImageServiceImpl.class);
    private static final String SLASH_END = File.separator.equals("\\") ? "\\\\$" : "/$";
    private static final String IMAGE_FOLDER_REGEX = ".+" + SLASH_END;

    private final FolderService folderService;
    private final ImageRepository imageRepository;

    @Value("${diploma.image-folder}")
    private String imageFolder;

    public ImageServiceImpl(ImageRepository imageRepository, FolderService folderService) {
        this.imageRepository = imageRepository;
        this.folderService = folderService;
    }

    @Override
    public byte[] getImageBytesById(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Image with such id: %s not exist", imageId)));

        byte[] imageBytes;
        try {
            imageBytes = folderService.getImageFromFolder(image.getPath());
        } catch (IOException ex) {
            LOG.error(String.format("Field to read image! Exception: %s ", ex));
            throw new RepositoryException(String.format("Field to read folder! Exception: %s", ex.getMessage()));
        }

        return imageBytes;
    }

    @Override
    @Transactional
    public Image saveImage(MultipartFile imageFile) {
        String path = getImageFolder(imageFolder) + imageFile.getOriginalFilename();

        try {
            folderService.saveImageToFolder(path, imageFile.getBytes());
        } catch (IOException ex) {
            LOG.error(String.format("Field to upload image. path: %s ", path), ex);
            throw new RepositoryException(String.format("Field to upload image! Exception: %s", ex.getMessage()));
        }

        return imageRepository.save(new Image(path));
    }

    @Override
    @Transactional
    public Image updateImage(MultipartFile imageFile, Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Image with such id: %d not exist", id)));

        String newPath = getImageFolder(imageFolder) + imageFile.getOriginalFilename();

        try {
            folderService.updateImageInFolder(image.getPath(), newPath, imageFile.getBytes());
        } catch (IOException ex) {
            LOG.error(String.format("Field to update image! Exception %s", ex.getMessage()));
            throw new RepositoryException(String.format("Field to update image! Exception: %s", ex.getMessage()));
        }

        image.setPath(newPath);

        return imageRepository.save(image);
    }

    @Override
    @Transactional
    public void deleteImageById(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Image with such id: %d not exist", id)));

        try {
            folderService.deleteImageFromFolder(image.getPath());
        } catch (IOException ex) {
            LOG.error(String.format("Field to delete image! Exception %s", ex.getMessage()));
            throw new RepositoryException(String.format("Field to delete image! Exception: %s", ex.getMessage()));
        }

        imageRepository.deleteById(id);
    }

    private String getImageFolder(String imageFolder) {
        if (!imageFolder.matches(IMAGE_FOLDER_REGEX)) {
            imageFolder += File.separator;
        }

        return imageFolder;
    }
}
