package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.ProductoInventarioRequest;
import com.example.MarketPost.entity.Inventario;
import com.example.MarketPost.entity.Producto;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.repository.InventarioRepository;
import com.example.MarketPost.response.ApiResponse;
import com.example.MarketPost.response.ResponseFactory;
import com.example.MarketPost.service.InventarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;

    @Override
    public void save(Producto producto) {
        Inventario inventario = new Inventario();

        inventario.setGestionActiva(false);
        inventario.setAlertaCritica(false);
        inventario.setNivelCritico(50);
        inventario.setUltimaCantidad(0);
        inventario.setCantidadActual(0);
        inventario.setProducto(producto);

        inventarioRepository.save(inventario);
    }

    @Override
    public void deleteByProductoId(Long productoId) {
        inventarioRepository.deleteByProductoId(productoId);
    }

    @Override
    public ApiResponse<ProductoInventarioRequest> getInventarioByProductoId(Long productoId) {
        var inventario = inventarioRepository
            .getInventarioByProductoId(productoId)
            .orElseThrow(() -> new ResourceNotFoundException(Inventario.class.getSimpleName(), productoId));

        return ResponseFactory.ok(inventario);
    }


}
