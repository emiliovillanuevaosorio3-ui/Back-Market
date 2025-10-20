package com.example.MarketPost.service;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.dto.SummaryProducto;

import java.util.List;

public interface ProductoService {
    List<SummaryProducto> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra);
    List<SummaryProducto> findByCategoriaId(Long categoriaId);
    ProductoRequest getDetalleProductoByProductoId(Long productoId);
    void saveProducto(ProductoRequest request);
    boolean deleteById(Long id);
}
