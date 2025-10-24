package com.example.MarketPost.service;

import com.example.MarketPost.dto.CategoriaRequest;
import com.example.MarketPost.entity.Categoria;
import com.example.MarketPost.response.ApiResponse;

public interface CategoriaService {
    void existsById(long id);
    Categoria getReferenceById(long id);
    ApiResponse<Long> save(CategoriaRequest request);
}
