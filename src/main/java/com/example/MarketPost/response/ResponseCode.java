package com.example.MarketPost.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK(0),
    NOT_FOUND(1),
    CONFLICT(-1),
    BAD_REQUEST(2); // <--- agregado
    private final Integer codigo;
}
