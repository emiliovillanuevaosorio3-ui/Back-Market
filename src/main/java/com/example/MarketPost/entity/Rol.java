package com.example.MarketPost.entity;

import com.example.MarketPost.audit.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "roles")
public class Rol extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolId;
    private String nombre;
    private Boolean estado;
}
