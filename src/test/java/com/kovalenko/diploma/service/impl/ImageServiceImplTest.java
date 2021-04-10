package com.kovalenko.diploma.service.impl;

import com.kovalenko.diploma.service.FolderService;
import com.kovalenko.diploma.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    private FolderService folderServiceMock;

    @Mock
    private ImageRepository imageRepositoryMock;

    @Mock
    MultipartFile imageFileMock;

    @InjectMocks
    private ImageServiceImpl imageServiceInjected;

    @Test
    void getImageFolderWithPathWithoutEndSlashShouldReturnFolderWithSlash() throws IOException {
        String imageFolder = "/folder1/folder2";
        ReflectionTestUtils.setField(imageServiceInjected, "imageFolder", imageFolder);
        String fileName = "image.png";
        String expectedPath = imageFolder + File.separator + fileName;

        MockMultipartFile mockMultipartFile = new MockMultipartFile("image.png", "image.png",
                "image", "".getBytes());

        imageServiceInjected.saveImage(mockMultipartFile);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(folderServiceMock).saveImageToFolder(captor.capture(), eq(mockMultipartFile.getBytes()));

        String actualPath = captor.getValue();

        assertThat(actualPath).isEqualTo(expectedPath);
    }

    @Test
    void getImageFolderWithPathWithEndSlashShouldReturnFolderWithSlash() throws IOException {
        String imageFolder = "/folder1/folder2" + File.separator;
        ReflectionTestUtils.setField(imageServiceInjected, "imageFolder", imageFolder);
        String fileName = "image.png";
        String expectedPath = imageFolder + fileName;

        MockMultipartFile mockMultipartFile = new MockMultipartFile("image.png", "image.png",
                "image", "".getBytes());

        imageServiceInjected.saveImage(mockMultipartFile);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(folderServiceMock).saveImageToFolder(captor.capture(), eq(mockMultipartFile.getBytes()));

        String actualPath = captor.getValue();

        assertThat(actualPath).isEqualTo(expectedPath);
    }
}
