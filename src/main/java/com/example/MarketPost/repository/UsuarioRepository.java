package com.example.MarketPost.repository;

import com.example.MarketPost.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
}