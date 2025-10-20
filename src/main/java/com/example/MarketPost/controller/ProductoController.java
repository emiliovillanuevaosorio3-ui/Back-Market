package com.example.MarketPost.controller;

import com.example.MarketPost.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Producto Controller")
@AllArgsConstructor
@RestController
@RequestMapping(value = "v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Operation(summary = "Buscar productos por el código de barra o descripción.")
    @GetMapping(value = "/search")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    public ResponseEntity<?> searchByDescripcionOrCodigoBarra(@RequestParam String descripcionOrCodigoBarra) {
        return ResponseEntity.ok(productoService.searchByDescripcionOrCodigoBarra(descripcionOrCodigoBarra));
    }

    @Operation(summary = "Obtener un producto específico por su Id")
    @GetMapping(value = "/{productoId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    public ResponseEntity<?> getDetalleProductoByProductoId(@PathVariable Long productoId) {
        return ResponseEntity.ok(productoService.getDetalleProductoByProductoId(productoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boolean eliminado = productoService.deleteById(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
