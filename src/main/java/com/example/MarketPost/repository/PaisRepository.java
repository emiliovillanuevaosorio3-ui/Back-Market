package com.example.MarketPost.repository;

import com.example.MarketPost.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    // Aquí puedes agregar consultas personalizadas si las necesitas
}
