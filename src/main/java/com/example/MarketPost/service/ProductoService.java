package com.example.MarketPost.service;

import com.example.MarketPost.dto.SummaryProducto;

import java.util.List;

public interface ProductoService {
    List<SummaryProducto> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra);
    List<SummaryProducto> findByCategoriaId(Long id);
    boolean deleteById(Long id);
}
