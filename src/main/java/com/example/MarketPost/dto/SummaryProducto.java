package com.example.MarketPost.dto;

import java.math.BigDecimal;

public record SummaryProducto(
    Long id,
    String descripcion,
    BigDecimal precio,
    Boolean estado
) { }
