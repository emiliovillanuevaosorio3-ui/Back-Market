package com.example.MarketPost.exception;

import com.example.MarketPost.response.ApiResponse;
import com.example.MarketPost.response.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class, produces = "application/json")
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ResponseFactory.notFound(resourceNotFoundException.getMessage()));
    }

}
