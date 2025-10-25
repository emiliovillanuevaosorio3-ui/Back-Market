package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.entity.Producto;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.mapper.ProductoMapper;
import com.example.MarketPost.repository.ProductoRepository;
import com.example.MarketPost.response.ApiResponse;
import com.example.MarketPost.response.ResponseFactory;
import com.example.MarketPost.service.CategoriaService;
import com.example.MarketPost.service.InventarioService;
import com.example.MarketPost.service.ProductoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaService categoriaService;
    private final InventarioService inventarioService;

    @Override
    public ApiResponse<List<SummaryProducto>> findByCategoriaId(Long categoriaId) {
        categoriaService.existsById(categoriaId);
        List<SummaryProducto> productos = productoRepository.findByCategoriaId(categoriaId);
        return ResponseFactory.ok(productos);
    }

    @Override
    public ApiResponse<ProductoRequest> getDetalleById(Long id) {
        ProductoRequest detalleProducto = productoRepository
            .getDetalleByProductoId(id)
            .orElseThrow(() -> new ResourceNotFoundException(Producto.class.getSimpleName(), id));

        return ResponseFactory.ok(detalleProducto);
    }

    @Transactional
    @Override
    public ApiResponse<Long> save(ProductoRequest request) {
        Producto productoEntity = productoMapper.mapToEntity(request);
        Producto newProducto = productoRepository.save(productoEntity);
        inventarioService.save(newProducto);
        return ResponseFactory.ok(newProducto.getProductoId());
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!productoRepository.existsByProductoId(id)) {
            throw new ResourceNotFoundException(Producto.class.getSimpleName(), id);
        }

        inventarioService.deleteByProductoId(id);
        productoRepository.deleteByProductoId(id);
    }

    @Override
    public ApiResponse<List<SummaryProducto>> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra) {
        List<SummaryProducto> productos = productoRepository.searchByDescripcionOrCodigoBarra(descripcionOrCodigoBarra);
        return ResponseFactory.ok(productos);
    }

}
