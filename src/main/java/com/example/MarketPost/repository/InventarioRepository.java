package com.example.MarketPost.repository;

import com.example.MarketPost.dto.ProductoInventarioRequest;
import com.example.MarketPost.entity.Inventario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface InventarioRepository extends Repository<Inventario, Long> {
    void save(Inventario inventario);
    void deleteByProductoId(Long productoId);

    @Query(value = """
        SELECT new com.example.MarketPost.dto.ProductoInventarioRequest(
            p.nombre,
            p.codigoBarra,
            p.precioCompra,
            i.gestionActiva,
            i.nivelCritico AS stockCritico,
            i.alertaCritica AS activarAlerta,
            i.cantidadActual,
            i.ultimaCantidad
        ) FROM Inventario i
        JOIN i.producto p
        WHERE p.productoId = :productoId
    """)
    Optional<ProductoInventarioRequest> getInventarioByProductoId(Long productoId);
}
