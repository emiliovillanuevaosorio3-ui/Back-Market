package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.CategoriaRequest;
import com.example.MarketPost.entity.Categoria;
import com.example.MarketPost.exception.ResourceDuplicateException;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.mapper.CategoriaMapper;
import com.example.MarketPost.repository.CategoriaRepository;
import com.example.MarketPost.response.ApiResponse;
import com.example.MarketPost.response.ResponseFactory;
import com.example.MarketPost.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public void existsById(long id) {
        if (!categoriaRepository.existsByCategoriaId(id)) {
            throw new ResourceNotFoundException(Categoria.class.getSimpleName(), id);
        }
    }

    @Override
    public Categoria getReferenceById(long id) {
        return categoriaRepository.getReferenceByCategoriaId(id);
    }

    @Override
    public ApiResponse<Long> save(CategoriaRequest request) {

        if (categoriaRepository.existsByNombre(request.nombre())) {
            throw new ResourceDuplicateException(Categoria.class.getSimpleName());
        }

        var categoriaEntity = categoriaMapper.MapToEntity(request);
        var categoriaId = categoriaRepository
            .save(categoriaEntity)
            .getCategoriaId();

        return ResponseFactory.ok(categoriaId);
    }


}
