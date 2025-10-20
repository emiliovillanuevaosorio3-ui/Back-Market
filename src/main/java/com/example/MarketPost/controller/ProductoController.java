package com.example.MarketPost.controller;

import com.example.MarketPost.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping(value = "v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping(value = "/search")
    public ResponseEntity<?> searchByDescripcionOrCodigoBarra(@RequestParam String descripcionOrCodigoBarra) {
        return ResponseEntity.ok(productoService.searchByDescripcionOrCodigoBarra(descripcionOrCodigoBarra));
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
