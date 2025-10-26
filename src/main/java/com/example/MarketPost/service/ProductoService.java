package com.example.MarketPost.service;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.response.ApiResponse;

import java.util.List;

public interface ProductoService {
    ApiResponse<List<SummaryProducto>> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra);
    ApiResponse<List<SummaryProducto>> findByCategoriaId(Long categoriaId);
    ApiResponse<ProductoRequest> getDetalleById(Long id);
    ApiResponse<Long> save(ProductoRequest request);
    void deleteById(Long id);
}
   