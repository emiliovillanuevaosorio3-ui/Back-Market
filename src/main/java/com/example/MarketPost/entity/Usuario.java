package com.example.MarketPost.entity;

import com.example.MarketPost.audit.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@Table(name = "usuarios")
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nroDocumento;
    private String nombre;
    private String username;
    private String nroTelefono;
    private String email;
    private Boolean estado;

    @Embedded
    private Audit auditoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rolId")
    private Rol rol;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paisId")
    private Pais pais;
}
