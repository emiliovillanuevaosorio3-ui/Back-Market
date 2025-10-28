package com.example.MarketPost.service;

import com.example.MarketPost.dto.UsuarioRequest;
import com.example.MarketPost.entity.Usuario;
import com.example.MarketPost.response.ApiResponse;

import java.util.List;

public interface UsuarioService {
    ApiResponse<Usuario> registrarUsuario(UsuarioRequest request);
    ApiResponse<List<Usuario>> findAll();
    void deleteById(Long id);
}