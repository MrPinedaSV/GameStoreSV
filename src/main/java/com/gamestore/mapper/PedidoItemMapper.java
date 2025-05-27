package com.gamestore.mapper;

import com.gamestore.dto.PedidoItemDTO;
import com.gamestore.entity.Pedido_Items;

public class PedidoItemMapper {
    public static PedidoItemDTO toDTO(Pedido_Items item) {
        PedidoItemDTO dto = new PedidoItemDTO();
        dto.setIdItem(item.getIdItem());
        dto.setIdPedido(item.getPedido().getIdPedido());
        dto.setIdVideojuego(item.getVideojuego().getIdVideojuego());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        dto.setVideojuego(VideoJuegoMapper.toDTO(item.getVideojuego()));
        return dto;
    }
}
