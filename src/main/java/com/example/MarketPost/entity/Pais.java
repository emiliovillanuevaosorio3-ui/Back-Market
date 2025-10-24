package com.example.MarketPost.entity;

import com.example.MarketPost.audit.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "paises")
@Entity
public class Pais extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paisId;
    private String nombre;
    private Boolean estado;
}
