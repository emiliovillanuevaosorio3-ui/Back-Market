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

import static com.example.MarketPost.util.Constant.*;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaService categoriaService;
    private final InventarioService inventarioService;

    @Override
    public ApiResponse<List<SummaryProducto>> findByCategoriaId(Long categoriaId) {
        categoriaService.existeCategoria(categoriaId);
        List<SummaryProducto> productos = productoRepository.findByCategoriaId(categoriaId);
        return ResponseFactory.ok(DATA_OK, productos);
    }

    @Override
    public ApiResponse<ProductoRequest> getDetalleProductoByProductoId(Long productoId) {
        ProductoRequest detalleProducto = productoRepository
            .getDetalleByProductoId(productoId)
            .orElseThrow(() -> new ResourceNotFoundException(Producto.class.getSimpleName(), productoId));

        return ResponseFactory.ok(DATA_OK, detalleProducto);
    }

    @Transactional
    @Override
    public ApiResponse<Long> saveProducto(ProductoRequest request) {
        Producto productoEntity = productoMapper.mapToEntity(request);
        Producto newProducto = productoRepository.save(productoEntity);
        inventarioService.save(newProducto);
        return ResponseFactory.ok(PRODUCTO_CREADO, newProducto.getProductoId());
    }

    @Transactional
    @Override
    public ApiResponse<Long> deleteById(Long id) {
        inventarioService.deleteByProductoId(id);
        productoRepository.deleteByProductoId(id);
        return ResponseFactory.ok(PRODUCTO_ELIMINADO, id);
    }


    @Override
    public List<SummaryProducto> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra) {
        return productoRepository.searchByDescripcionOrCodigoBarra(descripcionOrCodigoBarra);
    }

}
