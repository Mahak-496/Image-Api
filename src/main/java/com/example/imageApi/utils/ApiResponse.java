package com.example.imageApi.utils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse {
    private String message;
    private int statusCode;
    private Object data;
    private String devMessage;
}
