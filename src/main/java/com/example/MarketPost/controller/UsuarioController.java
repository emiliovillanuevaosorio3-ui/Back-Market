package com.example.MarketPost.controller;

import com.example.MarketPost.dto.UsuarioRequest;
import com.example.MarketPost.entity.Usuario;
import com.example.MarketPost.response.ApiResponse;
import com.example.MarketPost.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuario Controller")
@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Registrar un nuevo usuario")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201", description = "Usuario creado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409", description = "Email o username ya registrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500", description = "Error en el servidor")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<Usuario>> registrarUsuario(@RequestBody UsuarioRequest request) {
        ApiResponse<Usuario> response = usuarioService.registrarUsuario(request);

        HttpStatus status;
        switch (response.getCodigo()) {
            case 0 -> status = HttpStatus.CREATED;      // OK
            case -1 -> status = HttpStatus.CONFLICT;     // Conflicto
            default -> status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(response);
    }
}
