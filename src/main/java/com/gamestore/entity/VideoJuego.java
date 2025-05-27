package com.gamestore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "videojuegos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoJuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVideojuego;

    private String titulo;
    private String descripcion;
    private String plataforma;
    private Double precio;
    private Integer stock;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL)
    private List<Carrito_Items> carritoItems;

    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL)
    private List<Pedido_Items> pedidoItems;
}
