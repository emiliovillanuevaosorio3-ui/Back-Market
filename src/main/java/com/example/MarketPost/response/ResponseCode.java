package com.example.MarketPost.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK(0),
    NOT_FOUND(1),
    INTERNAL_SERVER_ERROR(-1);

    private final Integer codigo;
}
