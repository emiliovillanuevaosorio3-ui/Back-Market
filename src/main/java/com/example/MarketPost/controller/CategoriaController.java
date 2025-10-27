package com.example.MarketPost.controller;

import com.example.MarketPost.dto.CategoriaRequest;
import com.example.MarketPost.service.CategoriaService;
import com.example.MarketPost.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Categoria Controller")
@RestController
@RequestMapping(value = "v1/categorias")
@AllArgsConstructor
public class CategoriaController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

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

    @Operation(summary = "Registrar categoría")
    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "409", description = "Conflict"),
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(responseCode = "500", description = "Error server"),

    })
    public ResponseEntity<?> save(@RequestBody CategoriaRequest request) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(categoriaService.save(request));
    }
}
