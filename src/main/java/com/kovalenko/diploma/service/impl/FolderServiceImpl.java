package com.kovalenko.diploma.service.impl;

import com.kovalenko.diploma.service.FolderService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FolderServiceImpl implements FolderService {
    @Override
    public byte[] getImageFromFolder(String path) throws IOException {
        File folderInput = new File(path);

        return Files.readAllBytes(Paths.get(folderInput.getAbsolutePath()));
    }

    @Override
    public void saveImageToFolder(String path, byte[] imageBytes) throws IOException {
        Files.write(Paths.get(path), imageBytes);
    }

    @Override
    public void deleteImageFromFolder(String path) throws IOException {
        Files.delete(Paths.get(path));
    }

    @Override
    public void updateImageInFolder(String oldPath, String newPath, byte[] newImageBytes) throws IOException {
        deleteImageFromFolder(oldPath);
        saveImageToFolder(newPath, newImageBytes);
    }
}
