package com.example.MarketPost.repository;

import com.example.MarketPost.entity.Categoria;
import org.springframework.data.repository.Repository;

public interface CategoriaRepository extends Repository<Categoria, Long> {
    boolean existsByCategoriaId(Long categoriaId);
    Categoria getReferenceByCategoriaId(Long categoriaId);
    boolean existsByNombre(String nombre);
    Categoria save(Categoria categoria);
}
