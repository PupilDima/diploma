package com.kovalenko.diploma.service;

import java.io.IOException;

public interface FolderService {
    byte[] getImageFromFolder(String path) throws IOException;

    void saveImageToFolder(String path, byte[] imageBytes) throws IOException;

    void deleteImageFromFolder(String path) throws IOException;

    void updateImageInFolder(String oldPath, String newPath, byte[] newImageBytes) throws IOException;
}
