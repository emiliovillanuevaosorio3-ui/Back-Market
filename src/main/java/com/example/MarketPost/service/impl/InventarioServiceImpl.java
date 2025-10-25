package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.ProductoInventarioRequest;
import com.example.MarketPost.entity.Inventario;
import com.example.MarketPost.entity.Producto;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.repository.InventarioRepository;
import com.example.MarketPost.repository.ProductoRepository;
import com.example.MarketPost.response.ApiResponse;
import com.example.MarketPost.response.ResponseFactory;
import com.example.MarketPost.service.InventarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;

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

    @Transactional
    @Override
    public ApiResponse<Void> updateInventarioByProductoId(Long productoId, ProductoInventarioRequest request) {

        Producto producto = productoRepository
                .findByProductoId(productoId)
                .orElseThrow(() -> new ResourceNotFoundException(Producto.class.getSimpleName(), productoId));

        producto.setNombre(request.nombre());
        producto.setCodigoBarra(request.codigoBarras());
        producto.setPrecioCompra(request.precioCompra());

        Inventario inventario = inventarioRepository
                .findByProductoId(productoId)
                .orElseThrow(() -> new ResourceNotFoundException(Inventario.class.getSimpleName(), productoId));

        inventario.setUltimaCantidad(request.ultimaCantidad());
        inventario.setGestionActiva(request.gestionActiva());
        inventario.setNivelCritico(request.stockCritico());
        inventario.setAlertaCritica(request.activarAlerta());
        inventario.setCantidadActual(request.cantidadActual());

        return ResponseFactory.ok(null);
    }

}
