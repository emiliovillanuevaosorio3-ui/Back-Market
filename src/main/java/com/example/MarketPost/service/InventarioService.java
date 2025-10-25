package com.example.MarketPost.service;

import com.example.MarketPost.dto.ProductoInventarioRequest;
import com.example.MarketPost.response.ApiResponse;

public interface InventarioService {
    ApiResponse<ProductoInventarioRequest> getInventarioByProductoId(Long productoId);
    ApiResponse<Void> updateInventarioByProductoId(Long productoId, ProductoInventarioRequest request);
}
