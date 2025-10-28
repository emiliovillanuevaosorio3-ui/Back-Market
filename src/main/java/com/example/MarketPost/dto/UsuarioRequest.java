package com.example.MarketPost.dto;

public record UsuarioRequest(
        String nroDocumento,
        String nombre,
        String username,
        String nroTelefono,
        String email,
        Long rolId,
        Long paisId
) {}
