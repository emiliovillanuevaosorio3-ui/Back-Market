package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.repository.ProductoRepository;
import com.example.MarketPost.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<SummaryProducto> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra) {
        return productoRepository.searchByDescripcionOrCodigoBarra(descripcionOrCodigoBarra);
    }

    @Override
    public List<SummaryProducto> findByCategoriaId(Long id) {
        return productoRepository.findByCategoriaId(id);
    }

    @Override
    public boolean deleteById(Long id) {
        int filas = productoRepository.deleteByProductoId(id);
        return filas > 0;
    }

}
