package com.mcug.minichat.utils;

public class ApiResponse {
    public String status;
    public String message;
    public Object data;
    public Object errors;

    public ApiResponse(String status, String message, Object data, Object errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    // 快速创建成功和失败响应
    public static ApiResponse success(String message, Object data) {
        return new ApiResponse("success", message, data, null);
    }

    public static ApiResponse error(String message, Object errors) {
        return new ApiResponse("error", message, null, errors);
    }
}