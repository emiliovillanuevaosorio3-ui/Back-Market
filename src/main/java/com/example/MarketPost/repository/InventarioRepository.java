package com.example.MarketPost.repository;

import com.example.MarketPost.entity.Inventario;
import org.springframework.data.repository.Repository;

public interface InventarioRepository extends Repository<Inventario, Long> {
    void save(Inventario inventario);
    void deleteByProductoId(Long productoId);
}
