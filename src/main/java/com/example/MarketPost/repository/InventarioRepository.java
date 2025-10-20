package com.example.MarketPost.repository;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.entity.Inventario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface InventarioRepository extends Repository<Inventario, Long> {

    @Query(value = """
        SELECT new com.example.MarketPost.dto.ProductoRequest(
            p.nombre,
            p.codigoBarra,
            p.precio,
            i.precioCompra,
            p.precioDescontado,
            p.descripcion,
            p.activoOnline,
            p.estado,
            p.categoria.categoriaId
        )
        FROM Inventario i
        JOIN i.producto p
        WHERE p.productoId = :productoId
    """)
    Optional<ProductoRequest> getDetalleProductoByProductoId(Long productoId);
}
