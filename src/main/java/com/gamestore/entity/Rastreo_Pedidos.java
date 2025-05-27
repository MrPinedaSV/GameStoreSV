package com.gamestore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rastreo_pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rastreo_Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRastreo;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    private String estado;

    private LocalDateTime fecha = LocalDateTime.now();
}
