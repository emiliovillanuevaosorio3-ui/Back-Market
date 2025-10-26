package com.example.MarketPost.mapper;

import com.example.MarketPost.dto.CategoriaRequest;
import com.example.MarketPost.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria MapToEntity(CategoriaRequest request) {
        Categoria categoriaEntity = new Categoria();

        categoriaEntity.setNombre(request.nombre());
        categoriaEntity.setEstado(true);

        return categoriaEntity;
    }

}
