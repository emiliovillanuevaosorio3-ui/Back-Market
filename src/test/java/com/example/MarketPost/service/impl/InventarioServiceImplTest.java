package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.ProductoInventarioRequest;
import com.example.MarketPost.entity.Inventario;
import com.example.MarketPost.entity.Producto;
import com.example.MarketPost.exception.ResourceNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(value = MockitoExtension.class)
class InventarioServiceImplTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private InventarioServiceImpl inventarioService;

    private static ProductoInventarioRequest productoInventarioRequest;

    @BeforeAll
    static void setUp() {
        productoInventarioRequest = new ProductoInventarioRequest(
            1L,
            "Leche Gloria",
            "1234567890",
            new BigDecimal("3.50"),
            true,
            10,
            true,
            100,
            90
        );
    }

    @Test
    void givenProductoIdValid_whenGetInventarioByProductoId_thenReturnInventario() {
        when(inventarioRepository.getInventarioByProductoId(anyLong())).thenReturn(Optional.of(productoInventarioRequest));

        var result = inventarioService.getInventarioByProductoId(1L);

        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(ResponseCode.OK.getCodigo(), result.getCodigo()),
            () -> assertNotNull(result.getBody())
        );

        verify(inventarioRepository).getInventarioByProductoId(anyLong());
    }

    @Test
    void givenProductoIdInvalid_whenGetInventarioByProductoId_thenThrowException() {
        when(inventarioRepository.getInventarioByProductoId(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
           inventarioService.getInventarioByProductoId(1L);
        });

        verify(inventarioRepository).getInventarioByProductoId(anyLong());
    }

    @Test
    void givenProductoIdValid_whenUpdateInventarioByProductoId_thenReturnOk() {
        when(productoRepository.findByProductoId(anyLong())).thenReturn(Optional.of(new Producto()));
        when(inventarioRepository.findByProductoId(anyLong())).thenReturn(Optional.of(new Inventario()));

        var result = inventarioService.updateInventarioByProductoId(1L, productoInventarioRequest);

        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(ResponseCode.OK.getCodigo(), result.getCodigo())
        );

        verify(productoRepository).findByProductoId(anyLong());
        verify(inventarioRepository).findByProductoId(anyLong());
    }

    @Test
    void givenProductoIdInvalid_whenUpdateInventarioByProductoId_thenThrowException() {
        when(productoRepository.findByProductoId(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            inventarioService.updateInventarioByProductoId(1L, productoInventarioRequest);
        });

        verify(productoRepository).findByProductoId(anyLong());
    }
}