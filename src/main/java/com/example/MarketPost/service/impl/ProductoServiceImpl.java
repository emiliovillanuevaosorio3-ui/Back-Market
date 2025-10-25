package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.entity.Categoria;
import com.example.MarketPost.entity.Inventario;
import com.example.MarketPost.entity.Producto;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.repository.CategoriaRepository;
import com.example.MarketPost.repository.InventarioRepository;
import com.example.MarketPost.repository.ProductoRepository;
import com.example.MarketPost.response.ApiResponse;
import com.example.MarketPost.response.ResponseFactory;
import com.example.MarketPost.service.ProductoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final InventarioRepository inventarioRepository;

    @Override
    public ApiResponse<List<SummaryProducto>> findByCategoriaId(Long categoriaId) {
        if (!categoriaRepository.existsByCategoriaId(categoriaId))
            throw new ResourceNotFoundException(Categoria.class.getSimpleName(), categoriaId);

        var productos = productoRepository.findByCategoriaId(categoriaId);
        return ResponseFactory.ok(productos);
    }

    @Override
    public ApiResponse<ProductoRequest> getDetalleById(Long id) {
        var detalleProducto = productoRepository
            .getDetalleByProductoId(id)
            .orElseThrow(() -> new ResourceNotFoundException(Producto.class.getSimpleName(), id));

        return ResponseFactory.ok(detalleProducto);
    }

    @Transactional
    @Override
    public ApiResponse<Long> save(ProductoRequest request) {
        var producto = new Producto();
        producto.setNombre(request.nombre());
        producto.setCodigoBarra(request.codigoBarra());
        producto.setPrecio(request.precio());
        producto.setPrecioCompra(request.precioCompra());
        producto.setPrecioDescontado(request.precioDescontado());
        producto.setDescripcion(request.descripcion());
        producto.setActivoOnline(request.activoOnline());
        producto.setEstado(request.estado());
        producto.setCategoria(categoriaRepository.getReferenceByCategoriaId(request.categoriaId()));

        var newProducto = productoRepository.save(producto);

        var inventario = new Inventario();
        inventario.setGestionActiva(false);
        inventario.setAlertaCritica(false);
        inventario.setNivelCritico(50);
        inventario.setUltimaCantidad(0);
        inventario.setCantidadActual(0);
        inventario.setProducto(producto);

        inventarioRepository.save(inventario);

        return ResponseFactory.ok(newProducto.getProductoId());
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!productoRepository.existsByProductoId(id))
            throw new ResourceNotFoundException(Producto.class.getSimpleName(), id);

        inventarioRepository.deleteByProductoId(id);
        productoRepository.deleteByProductoId(id);
    }

    @Override
    public ApiResponse<List<SummaryProducto>> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra) {
        var productos = productoRepository.searchByDescripcionOrCodigoBarra(descripcionOrCodigoBarra);
        return ResponseFactory.ok(productos);
    }

}
