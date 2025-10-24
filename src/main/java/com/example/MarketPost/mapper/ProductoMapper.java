package com.example.MarketPost.mapper;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.entity.Producto;
import com.example.MarketPost.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductoMapper {

    private final CategoriaService categoriaService;

    public Producto mapToEntity(ProductoRequest request) {
        Producto productoEntity = new Producto();

        productoEntity.setNombre(request.nombre());
        productoEntity.setCodigoBarra(request.codigoBarra());
        productoEntity.setPrecio(request.precio());
        productoEntity.setPrecioCompra(request.precioCompra());
        productoEntity.setPrecioDescontado(request.precioDescontado());
        productoEntity.setDescripcion(request.descripcion());
        productoEntity.setActivoOnline(request.activoOnline());
        productoEntity.setEstado(request.estado());
        productoEntity.setCategoria(categoriaService.getReferenceById(request.categoriaId()));

        return productoEntity;
    }


}
