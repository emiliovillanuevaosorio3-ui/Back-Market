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
@Entity
@Table(name = "productos")
@EntityListeners(AuditingEntityListener.class)
public class Producto extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    private String nombre;
    private String codigoBarra;
    private BigDecimal precio;
    private BigDecimal precioCompra;
    private BigDecimal precioDescontado;
    private Boolean estado;
    private String descripcion;
    private Boolean activoOnline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;
}
