package com.gamestore.mapper;

import com.gamestore.dto.PedidoDTO;
import com.gamestore.entity.Pedido;

public class PedidoMapper {
    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(pedido.getIdPedido());
        dto.setIdUsuario(pedido.getUsuario().getIdUsuario());
        dto.setTotal(pedido.getTotal());
        dto.setEstado(pedido.getEstado());
        dto.setFecha(pedido.getFecha());
        return dto;
    }
}
