package com.gamestore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrito_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrito_Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_videojuego", nullable = false)
    private VideoJuego videojuego;

    private Integer cantidad;
}
