package com.goros._9_keo_vuththana_springboot_homework003.utils;

import com.goros._9_keo_vuththana_springboot_homework003.model.response.ApiResponse;
import com.goros._9_keo_vuththana_springboot_homework003.model.response.ApiResponseVoid;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class ResponseUtil {
    public static <T> ApiResponse<T> success(HttpStatus status, String message, T payload) {
        return ApiResponse.<T>builder().success(true).status(status).message(message).payload(payload).timestamp(Instant.now()).build();
    }

    public static <T> ApiResponse<T> notFoundError(HttpStatus status, String message) {
        return ApiResponse.<T>builder().success(false).status(status).message(message).timestamp(Instant.now()).build();
    }

    public static ApiResponseVoid successVoid(HttpStatus status, String message) {
        return ApiResponseVoid.builder().success(true).status(status).message(message).timestamp(Instant.now()).build();
    }

    public static ApiResponseVoid errorVoid(HttpStatus status, String message) {
        return ApiResponseVoid.builder().success(false).status(status).message(message).timestamp(Instant.now()).build();
    }
}
