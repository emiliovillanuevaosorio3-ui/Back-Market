package com.example.MarketPost.controller;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.response.ResponseFactory;
import com.example.MarketPost.service.CategoriaService;
import com.example.MarketPost.service.ProductoService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CategoriaController.class)
class CategoriaControllerTest {

    private static final String BASE_PATH = "/v1/categorias";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @MockitoBean
    private CategoriaService categoriaService;

    @SneakyThrows
    @Test
    void givenCategoriaIdValid_whenGetProductosByCategoriaId_thenReturnOk() {
        List<SummaryProducto> productos = new ArrayList<>();
        var expectedResult = ResponseFactory.ok(productos);

        when(productoService.findByCategoriaId(anyLong())).thenReturn(expectedResult);

        mockMvc.perform(get(BASE_PATH + "/{categoriaId}/productos", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void givenCategoriaRequest_whenSave_thenReturnCreated() {
        var request = """
            { "nombre": "New Categoria" }
        """;

        var expectedResult = ResponseFactory.ok(1L);
        when(productoService.save(any(ProductoRequest.class))).thenReturn(expectedResult);

        mockMvc.perform(post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(request))
            .andExpect(status().isCreated());

    }

    @SneakyThrows
    @Test
    void givenCategoriaIdInvalid_whenGetProductosByCategoriaId_thenReturnNotFound() {
        when(productoService.findByCategoriaId(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(BASE_PATH + "/{categoriaId}/productos", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}