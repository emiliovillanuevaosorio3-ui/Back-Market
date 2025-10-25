package com.example.MarketPost.service;

import com.example.MarketPost.dto.CategoriaRequest;
import com.example.MarketPost.response.ApiResponse;

public interface CategoriaService {
    ApiResponse<Long> save(CategoriaRequest request);
}
