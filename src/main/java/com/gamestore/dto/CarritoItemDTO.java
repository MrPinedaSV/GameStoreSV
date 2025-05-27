package com.gamestore.dto;

import lombok.Data;

@Data
public class CarritoItemDTO {
    private Integer idItem;
    private  Integer IdCarrito;
    private Integer IdVideojuego;
    private Integer cantidad;
    private VideoJuegoDTO videojuego;
}
