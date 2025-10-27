package com.example.MarketPost.controller;

import com.example.MarketPost.dto.ProductoInventarioRequest;
import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.response.ResponseFactory;
import com.example.MarketPost.service.InventarioService;
import com.example.MarketPost.service.ProductoService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProductoController.class)
class ProductoControllerTest {

    private static String basePath;
    private static ProductoRequest productoRequest;
    private static Long productoId;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @MockitoBean
    private InventarioService inventarioService;

    @BeforeAll
    static void setUp() {
        basePath = "/v1/productos";
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

    @SneakyThrows
    @Test
    void givenCodigoBarraOrDescripcion_whenSearchByDescripcionOrCodigoBarra_thenReturnOk() {
        List<SummaryProducto> productos = new ArrayList<>();
        var expectedResult = ResponseFactory.ok(productos);

        when(productoService.searchByDescripcionOrCodigoBarra(anyString())).thenReturn(expectedResult);

        mockMvc.perform(get(basePath + "/search")
                .queryParam("descripcionOrCodigoBarra", "Yogurt")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void givenProductoIdValid_whenGetDetalleProductoByProductoId_thenReturnOk() {
        when(productoService.getDetalleById(anyLong())).thenReturn(ResponseFactory.ok(productoRequest));

        mockMvc.perform(get(basePath + "/{productoId}", productoId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @SneakyThrows
    @Test
    void givenProductoIdInvalid_whenGetDetalleProductoByProductoId_thenReturnNotFound() {
        when(productoService.getDetalleById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(basePath + "/{productoId}", productoId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @SneakyThrows
    @Test
    void givenProductoIdValid_whenDeleteById_thenReturnNoContent() {
        doNothing().when(productoService).deleteById(anyLong());

        mockMvc.perform(delete(basePath + "/{id}", productoId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @SneakyThrows
    @Test
    void givenProductoIdInvalid_whenDeleteById_thenReturnNotFound() {
        doThrow(ResourceNotFoundException.class).when(productoService).deleteById(anyLong());

        mockMvc.perform(delete(basePath + "/{id}", productoId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @SneakyThrows
    @Test
    void givenProductoRequest_whenSave_thenReturnCreated() {
        var request = """
            {
                "nombre": "Yogurt",
                "codigoBarra": "12345",
                "precio": 5.5,
                "precioCompra": 5.0,
                "precioDescontado": 0,
                "descripcion": "",
                "activoOnline": true,
                "estado": true,
                "categoriaId": 1
            }
        """;
        when(productoService.save(any(ProductoRequest.class))).thenReturn(ResponseFactory.ok(productoId));

        mockMvc.perform(post(basePath)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated());
    }

    @SneakyThrows
    @Test
    void givenProductIdValid_whenUpdateInventarioByProductoId_thenReturnOk() {
        var request = """
        {
            "productoId": 1,
            "nombre": "Yogurt",
            "codigoBarras": "12345",
            "precioCompra": 5.0,
            "gestionActiva": true,
            "stockCritico": 100,
            "activarAlerta": true,
            "cantidadActual": 100,
            "ultimaCantidad": 50
        }
        """;

        when(inventarioService.updateInventarioByProductoId(anyLong(), any(ProductoInventarioRequest.class))).thenReturn(ResponseFactory.ok(null));

        mockMvc.perform(put(basePath + "/{id}/inventario", productoId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }
}