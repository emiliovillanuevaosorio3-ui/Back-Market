package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.CategoriaRequest;
import com.example.MarketPost.entity.Categoria;
import com.example.MarketPost.exception.ResourceDuplicateException;
import com.example.MarketPost.mapper.CategoriaMapper;
import com.example.MarketPost.repository.CategoriaRepository;
import com.example.MarketPost.response.ResponseCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.MarketPost.util.Constant.DATA_OK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Test
    void giveNewCategoria_whenSave_thenReturnOk() {
        when(categoriaRepository.existsByNombre(anyString())).thenReturn(false);
        when(categoriaMapper.MapToEntity(any(CategoriaRequest.class))).thenReturn(new Categoria());
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(new Categoria());

        var result = categoriaService.save(new CategoriaRequest(""));

        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(ResponseCode.OK.getCodigo(), result.getCodigo()),
            () -> assertEquals(DATA_OK, result.getMensaje())
        );

        verify(categoriaRepository).existsByNombre(anyString());
        verify(categoriaMapper).MapToEntity(any(CategoriaRequest.class));
        verify(categoriaRepository).save((any(Categoria.class)));
    }

    @Test
    void giveCategoriaExistente_whenSave_thenThrowException() {
        when(categoriaRepository.existsByNombre(anyString())).thenReturn(true);

        assertThrows(ResourceDuplicateException.class, () -> {
           categoriaService.save(new CategoriaRequest(""));
        });

        verify(categoriaRepository).existsByNombre(anyString());
    }
}