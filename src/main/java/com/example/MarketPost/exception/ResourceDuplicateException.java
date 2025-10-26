package com.example.MarketPost.exception;

import com.example.MarketPost.util.Constant;

public class ResourceDuplicateException extends RuntimeException {
    public ResourceDuplicateException(String resourceType) {
        super(String.format(Constant.MSG_RESOURCE_DUPLICATE, resourceType));
    }
}
