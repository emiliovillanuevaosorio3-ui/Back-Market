package com.example.MarketPost.dto;

import java.math.BigDecimal;

public record ProductoInventarioRequest(
    Long productoId,
    String nombre,
    String codigoBarras,
    BigDecimal precioCompra,
    Boolean gestionActiva,
    Integer stockCritico,
    Boolean activarAlerta,
    Integer cantidadActual,
    Integer ultimaCantidad
) { }
