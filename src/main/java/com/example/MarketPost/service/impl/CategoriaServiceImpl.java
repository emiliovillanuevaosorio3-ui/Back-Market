package com.example.MarketPost.service.impl;

import com.example.MarketPost.entity.Categoria;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.repository.CategoriaRepository;
import com.example.MarketPost.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public void existeCategoria(long id) {
        if (!categoriaRepository.existsByCategoriaId(id)) {
            throw new ResourceNotFoundException(Categoria.class.getSimpleName(), id);
        }
    }

    @Override
    public Categoria getReferenceById(long id) {
        return categoriaRepository.getReferenceByCategoriaId(id);
    }
}
