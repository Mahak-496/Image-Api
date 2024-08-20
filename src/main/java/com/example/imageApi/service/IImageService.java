package com.example.imageApi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {

    String uploadImage(MultipartFile imageFile) throws IOException;

    byte[] downloadImage(String imageName) throws IOException;

}
