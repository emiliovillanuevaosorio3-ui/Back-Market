package com.example.MarketPost.response;

import static com.example.MarketPost.util.Constant.DATA_OK;

public class ResponseFactory {

    private ResponseFactory() {
        throw new UnsupportedOperationException("Class utility");
    }

    public static <T> ApiResponse<T> ok(T body) {
        return new ApiResponse<>(ResponseCode.OK, DATA_OK, body);
    }

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(ResponseCode.OK, DATA_OK, null);
    }

    public static ApiResponse<Void> badRequest(String mensaje) {
        return new ApiResponse<>(ResponseCode.BAD_REQUEST, mensaje, null);
    }

    public static <T> ApiResponse<T> of(ResponseCode responseCode, String mensaje) {
        return new ApiResponse<>(responseCode, mensaje, null);
    }

    public static <T> ApiResponse<T> of(ResponseCode responseCode, String mensaje, T body) {
        return new ApiResponse<>(responseCode, mensaje, body);
    }
}
