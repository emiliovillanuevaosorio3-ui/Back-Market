package com.example.MarketPost.entity;

import com.example.MarketPost.audit.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Table(name = "inventarios")
@Entity
public class Inventario extends Audit {
    @Id
    private Long productoId;

    private Integer cantidadActual;
    private Integer ultimaCantidad;
    private Integer nivelCritico;
    private Boolean gestionActiva;
    private Boolean alertaCritica;

    @OneToOne
    @JoinColumn(name = "productoId")
    @MapsId
    private Producto producto;
}
