package com.example.MarketPost.response;

import static com.example.MarketPost.util.Constant.DATA_OK;

public class ResponseFactory {

    private ResponseFactory() {
        throw new UnsupportedOperationException("Class ut  utility");
    }

    public static <T> ApiResponse<T> ok(T data) {
        return ok(DATA_OK, data);
    }

    public static <T> ApiResponse<T> ok(String mensaje, T data) {
        return new ApiResponse<>(ResponseCode.OK, mensaje, data);
    }

    public static <Void> ApiResponse<Void> notFound(String mensaje) {
        return new ApiResponse<>(ResponseCode.NOT_FOUND, mensaje);
    }

}
