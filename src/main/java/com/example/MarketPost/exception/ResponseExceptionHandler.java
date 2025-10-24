package com.example.MarketPost.exception;

import com.example.MarketPost.response.ApiResponse;
import com.example.MarketPost.response.ResponseCode;
import com.example.MarketPost.response.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiResponse<Void> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseFactory.of(ResponseCode.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(value = ResourceDuplicateException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ApiResponse<Void> handleResourceDuplicateException(ResourceDuplicateException ex) {
        return ResponseFactory.of(ResponseCode.CONFLICT, ex.getMessage());
    }

}
