package com.example.MarketPost.controller;

import com.example.MarketPost.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Categoria Controller")
@RestController
@RequestMapping(value = "v1/categorias")
@AllArgsConstructor
public class CategoriaController {

    private final ProductoService productoService;

    @Operation(summary = "Obtener los productos de una categoria")
    @GetMapping(value = "/{categoriaId}/productos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "Categoria no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    public ResponseEntity<?> getProductosByCategoriaId(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoService.findByCategoriaId(categoriaId));
    }
}
