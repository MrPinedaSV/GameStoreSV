package com.gamestore.dto;

import lombok.Data;

@Data
public class PedidoItemDTO {
    private Integer idItem;
    private Integer idPedido;
    private Integer idVideojuego;
    private String titulo;
    private Integer cantidad;
    private Double precioUnitario;
    private VideoJuegoDTO videojuego;
}
