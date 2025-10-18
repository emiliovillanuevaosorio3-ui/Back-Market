package com.example.MarketPost.repository;

import com.example.MarketPost.dto.SummaryProducto;
import com.example.MarketPost.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ProductoRepository extends Repository<Producto, Long> {

    @Query(value = """
        SELECT new com.example.MarketPost.dto.SummaryProducto(
            productoId AS id,
            rutaImagen,
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
            rutaImagen,
            descripcion,
            precio,
            estado
        )
        FROM Producto
        WHERE categoria.categoriaId = :categoriaId
    """)
    List<SummaryProducto> findByCategoriaId(Long categoriaId);

}
