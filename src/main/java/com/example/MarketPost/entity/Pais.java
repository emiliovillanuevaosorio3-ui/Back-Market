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
@Table(name = "paises")
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paisId;
    private String nombre;
    private Boolean estado;

    @Embedded
    private Audit auditoria;
}
