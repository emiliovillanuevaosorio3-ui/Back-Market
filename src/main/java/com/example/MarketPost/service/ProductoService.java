package com.example.MarketPost.service;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.response.ApiResponse;

import java.util.List;

public interface ProductoService {
    List<SummaryProducto> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra);
    ApiResponse<List<SummaryProducto>> findByCategoriaId(Long categoriaId);
    ApiResponse<ProductoRequest> getDetalleProductoByProductoId(Long productoId);
    ApiResponse<Long> saveProducto(ProductoRequest request);
    ApiResponse<Long> deleteById(Long id);
}
   