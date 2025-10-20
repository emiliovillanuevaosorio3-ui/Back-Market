package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.repository.InventarioRepository;
import com.example.MarketPost.repository.ProductoRepository;
import com.example.MarketPost.service.ProductoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final InventarioRepository inventarioRepository;

    @Override
    public List<SummaryProducto> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra) {
        return productoRepository.searchByDescripcionOrCodigoBarra(descripcionOrCodigoBarra);
    }

    @Override
    public List<SummaryProducto> findByCategoriaId(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public ProductoRequest getDetalleProductoByProductoId(Long productoId) {
        return inventarioRepository
            .getDetalleProductoByProductoId(productoId)
            .orElseThrow(() -> new IllegalArgumentException(""));
    }

    @Transactional
    @Override
    public void saveProducto(ProductoRequest request) {

    }

    @Override
    public boolean deleteById(Long id) {
        int filas = productoRepository.deleteByProductoId(id);
        return filas > 0;
    }

}
