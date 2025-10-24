package com.example.MarketPost.service;

import com.example.MarketPost.entity.Categoria;

public interface CategoriaService {
    void existsById(long id);
    Categoria getReferenceById(long id);
}
