package com.example.MarketPost.controller;

import com.example.MarketPost.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(value = "v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping(value = "/search")
    public ResponseEntity<?> searchByDescripcionOrCodigoBarra(@RequestParam String descripcionOrCodigoBarra) {
        return ResponseEntity.ok(productoService.searchByDescripcionOrCodigoBarra(descripcionOrCodigoBarra));
    }


}
