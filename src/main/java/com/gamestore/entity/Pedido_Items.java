package com.gamestore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pedido_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido_Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_videojuego", nullable = false)
    private VideoJuego videojuego;

    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;
}
