package com.example.imageApi.service;

import com.example.imageApi.entity.Image;
import com.example.imageApi.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Value("${file.storage.location}")
    private String storageLocation;


    @Override
    public String uploadImage(MultipartFile imageFile) throws IOException {
        String fileName = imageFile.getOriginalFilename();
        Path filePath = Paths.get(storageLocation, fileName);

        Image imageToSave = Image.builder()
                .fileName(fileName)
                .fileType(imageFile.getContentType())
                .filePath(filePath.toString())
                .build();
        imageRepository.save(imageToSave);

        return "File uploaded successfully: " + fileName;
    }


    @Override
    public byte[] downloadImage(String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Invalid file name");
        }

        Optional<Image> dbImage = imageRepository.findByfileName(fileName);
        if (dbImage.isEmpty()) {
            throw new RuntimeException("Image not found with file name: " + fileName);
        }

        Path filePath = Paths.get(storageLocation, fileName);
        if (Files.notExists(filePath)) {
            throw new RuntimeException("File does not exist at path: " + filePath);
        }

        return Files.readAllBytes(filePath);
    }
}
