package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.entity.Categoria;
import com.example.MarketPost.entity.Inventario;
import com.example.MarketPost.entity.Producto;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.repository.CategoriaRepository;
import com.example.MarketPost.repository.InventarioRepository;
import com.example.MarketPost.repository.ProductoRepository;
import com.example.MarketPost.response.ResponseCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(value = MockitoExtension.class)
class ProductoServiceImplTest {

    private static Long categoriaId;
    private static Long productoId;
    private static ProductoRequest productoRequest;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @BeforeAll
    static void setUp() {
        categoriaId = 1L;
        productoId = 1L;
        productoRequest = new ProductoRequest(
            "Test product",
            "12",
            BigDecimal.ONE,
            BigDecimal.ONE,
            BigDecimal.ONE,
            "test",
            true,
            true,
            1L
        );
    }

    @Test
    void givenCategoriaId_whenFindByCategoriaId_thenReturnOk() {
        when(categoriaRepository.existsByCategoriaId(anyLong())).thenReturn(true);
        when(productoRepository.findByCategoriaId(anyLong())).thenReturn(List.of());

        var result = productoService.findByCategoriaId(categoriaId);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNotNull(result.getBody()),
            () -> assertEquals(ResponseCode.OK.getCodigo(), result.getCodigo())
        );

        verify(categoriaRepository).existsByCategoriaId(anyLong());
        verify(productoRepository).findByCategoriaId(anyLong());
    }

    @Test
    void givenCategoriaIdInvalid_whenFindByCategoriaId_thenThrowException() {
        when(categoriaRepository.existsByCategoriaId(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            productoService.findByCategoriaId(categoriaId);
        });

        verify(categoriaRepository).existsByCategoriaId(anyLong());
    }

    @Test
    void givenProductoId_whenGetDetalleById_thenReturnOk() {
        when(productoRepository.getDetalleByProductoId(anyLong())).thenReturn(Optional.of(productoRequest));

        var result = productoService.getDetalleById(productoId);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNotNull(result.getBody()),
            () -> assertEquals(ResponseCode.OK.getCodigo(), result.getCodigo())
        );

        verify(productoRepository).getDetalleByProductoId(anyLong());
    }

    @Test
    void givenProductoIdInvalid_whenGetDetalleById_thenReturnOk() {
        when(productoRepository.getDetalleByProductoId(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productoService.getDetalleById(productoId);
        });
    }

    @Test
    void givenProductoRequest_whenSave_thenReturnOk() {
        when(categoriaRepository.getReferenceByCategoriaId(anyLong())).thenReturn(new Categoria());
        when(productoRepository.save(any(Producto.class))).thenReturn(new Producto());
        doNothing().when(inventarioRepository).save(any(Inventario.class));

        var result = productoService.save(productoRequest);

        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(ResponseCode.OK.getCodigo(), result.getCodigo())
        );

        verify(categoriaRepository).getReferenceByCategoriaId(anyLong());
        verify(productoRepository).save(any(Producto.class));
        verify(inventarioRepository).save(any(Inventario.class));
    }

    @Test
    void givenProductoIdExistente_whenDeleteById_thenEliminarProducto() {
        when(productoRepository.existsByProductoId(anyLong())).thenReturn(true);
        doNothing().when(inventarioRepository).deleteByProductoId(anyLong());
        doNothing().when(productoRepository).deleteByProductoId(anyLong());

        productoService.deleteById(productoId);

        verify(productoRepository).existsByProductoId(anyLong());
        verify(inventarioRepository).deleteByProductoId(anyLong());
        verify(productoRepository).deleteByProductoId(anyLong());
    }

    @Test
    void givenDescripcionOrCodigoBarra_whenSearchByDescripcionOrCodigoBarra_thenReturnProductos() {
        when(productoRepository.searchByDescripcionOrCodigoBarra(anyString())).thenReturn(List.of());

        var result = productoService.searchByDescripcionOrCodigoBarra("");

        assertAll(
            () -> assertNotNull(result),
            () -> assertTrue(result.getBody().isEmpty())
        );

        verify(productoRepository).searchByDescripcionOrCodigoBarra(anyString());
    }
}