package com.example.MarketPost.entity;

import com.example.MarketPost.audit.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "usuarios")
@Entity
public class Usuario extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    private String nroDocumento;
    private String nombre;
    private String username;
    private String nroTelefono;
    private String email;
    private Boolean estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rolId")
    private Rol rol;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paisId")
    private Pais pais;
}
