package com.example.MarketPost.exception;

import static com.example.MarketPost.util.Constant.MSG_RESOURCE_NOT_FOUND;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceType, Long id) {
        super(String.format(MSG_RESOURCE_NOT_FOUND, resourceType, id));
    }
}
