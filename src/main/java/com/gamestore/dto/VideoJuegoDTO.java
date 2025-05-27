package com.gamestore.dto;

import lombok.Data;

@Data
public class VideoJuegoDTO {
    private Integer idVideojuego;
    private String titulo;
    private String descripcion;
    private String plataforma;
    private Double precio;
    private Integer stock;
    private String imagenUrl;
}
