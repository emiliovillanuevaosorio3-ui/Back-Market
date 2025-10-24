package com.example.MarketPost.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private final Integer codigo;
    private final String mensaje;
    private final T body;

    public ApiResponse(ResponseCode responseCode, String mensaje) {
        this(responseCode.getCodigo(), mensaje, null);
    }

    public ApiResponse(ResponseCode responseCode, String mensaje, T body) {
        this(responseCode.getCodigo(), mensaje, body);
    }
}
