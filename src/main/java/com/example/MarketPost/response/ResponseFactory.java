package com.example.MarketPost.response;

public class ResponseFactory {

    private ResponseFactory() {
        throw new UnsupportedOperationException("Class ut  utility");
    }

    public static <T> ApiResponse<T> ok(String mensaje, T data) {
        return new ApiResponse<>(ResponseCode.OK, mensaje, data);
    }

    public static <Void> ApiResponse<Void> notFound(String mensaje) {
        return new ApiResponse<>(ResponseCode.NOT_FOUND, mensaje);
    }

}
