package com.example.MarketPost.service;

import com.example.MarketPost.dto.ProductoInventarioRequest;
import com.example.MarketPost.entity.Producto;
import com.example.MarketPost.response.ApiResponse;

public interface InventarioService {
    void save(Producto producto);
    void deleteByProductoId(Long productoId);
    ApiResponse<ProductoInventarioRequest> getInventarioByProductoId(Long productoId);
}
