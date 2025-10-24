package com.example.MarketPost.service;

import com.example.MarketPost.entity.Categoria;

public interface CategoriaService {
    void existeCategoria(long id);
    Categoria getReferenceById(long id);
}
