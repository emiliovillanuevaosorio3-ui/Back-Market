package com.example.MarketPost.repository;

import com.example.MarketPost.dto.ProductoRequest;
import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.entity.Producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends Repository<Producto, Long> {

    @Query(value = """
        SELECT new com.example.MarketPost.dto.SummaryProducto(
            productoId AS id,
            descripcion,
            precio,
            estado
        )
        FROM Producto
        WHERE descripcion LIKE %:descripcionOrCodigoBarra%
          OR codigoBarra LIKE %:descripcionOrCodigoBarra%
    """)
    List<SummaryProducto> searchByDescripcionOrCodigoBarra(String descripcionOrCodigoBarra);

    @Query(value = """
        SELECT new com.example.MarketPost.dto.SummaryProducto(
            productoId AS id,
            descripcion,
            precio,
            estado
        )
        FROM Producto
        WHERE categoria.categoriaId = :categoriaId
    """)
    List<SummaryProducto> findByCategoriaId(Long categoriaId);

    @Query(value = """
        SELECT new com.example.MarketPost.dto.ProductoRequest(
            nombre,
            codigoBarra,
            precio,
            precioCompra,
            precioDescontado,
            descripcion,
            activoOnline,
            estado,
            categoria.categoriaId
        )
        FROM Producto
        WHERE productoId = :productoId
    """)
    Optional<ProductoRequest> getDetalleByProductoId(Long productoId);

    Producto save(Producto producto);

    void deleteByProductoId(Long id);
}
