package com.example.MarketPost.dto;

import java.math.BigDecimal;

public record ProductoRequest(
    String nombre,
    String codigoBarra,
    BigDecimal precio,
    BigDecimal precioCompra,
    BigDecimal precioDescontado,
    String descripcion,
    Boolean activoOnline,
    Boolean estado,
    Long categoriaId
) { }
