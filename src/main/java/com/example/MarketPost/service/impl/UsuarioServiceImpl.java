package com.example.MarketPost.service.impl;

import com.example.MarketPost.dto.UsuarioRequest;
import com.example.MarketPost.entity.Pais;
import com.example.MarketPost.entity.Rol;
import com.example.MarketPost.entity.Usuario;
import com.example.MarketPost.exception.ResourceNotFoundException;
import com.example.MarketPost.repository.PaisRepository;
import com.example.MarketPost.repository.RolRepository;
import com.example.MarketPost.repository.UsuarioRepository;
import com.example.MarketPost.response.ApiResponse;
import com.example.MarketPost.response.ResponseCode;
import com.example.MarketPost.response.ResponseFactory;
import com.example.MarketPost.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PaisRepository paisRepository;

    @Transactional
    @Override
    public ApiResponse<Usuario> registrarUsuario(UsuarioRequest request) {
        // Validar duplicados
        if (usuarioRepository.existsByEmail(request.email()) || usuarioRepository.existsByUsername(request.username())) {
            return ResponseFactory.of(ResponseCode.CONFLICT, "Email o username ya registrado");
        }

        Rol rol = rolRepository.findById(request.rolId())
                .orElseThrow(() -> new ResourceNotFoundException(Rol.class.getSimpleName(), request.rolId()));

        Pais pais = paisRepository.findById(request.paisId())
                .orElseThrow(() -> new ResourceNotFoundException(Pais.class.getSimpleName(), request.paisId()));

        Usuario usuario = new Usuario();
        usuario.setNroDocumento(request.nroDocumento());
        usuario.setNombre(request.nombre());
        usuario.setUsername(request.username());
        usuario.setNroTelefono(request.nroTelefono());
        usuario.setEmail(request.email());
        usuario.setEstado(true); // activo por defecto
        usuario.setRol(rol);
        usuario.setPais(pais);

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseFactory.ok(nuevoUsuario);
    }

    @Override
    public ApiResponse<List<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseFactory.ok(usuarios);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException(Usuario.class.getSimpleName(), id);
        }
        usuarioRepository.deleteById(id);
    }
}
