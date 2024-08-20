package com.example.imageApi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ImageResponse {
    private String fileName;
    private String downloadUrl;
    private String fileType;
    private long fileSize;
}
