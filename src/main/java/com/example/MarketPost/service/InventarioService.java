package com.example.MarketPost.service;

import com.example.MarketPost.entity.Producto;

public interface InventarioService {
    void save(Producto producto);
    void deleteByProductoId(Long productoId);
}
