package com.example.MarketPost.entity;

import com.example.MarketPost.audit.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Table(name = "inventarios")
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Inventario {
    @Id
    private Long id;

    private Integer cantidadActual;
    private Integer ultimaCantidad;
    private Integer nivelCritico;
    private Boolean gestionActiva;
    private Boolean alertaCritica;
    private BigDecimal precioCompra;

    @Embedded
    private Audit auditoria;

    @OneToOne
    @JoinColumn(name = "productoId")
    @MapsId
    private Producto producto;
}
