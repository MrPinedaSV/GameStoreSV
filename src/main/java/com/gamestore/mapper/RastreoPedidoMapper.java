package com.gamestore.mapper;

import com.gamestore.dto.RastreoPedidoDTO;
import com.gamestore.entity.Rastreo_Pedidos;

public class RastreoPedidoMapper {
    public static RastreoPedidoDTO toDTO(Rastreo_Pedidos r) {
        RastreoPedidoDTO dto = new RastreoPedidoDTO();
        dto.setIdRastreo(r.getIdRastreo());
        dto.setIdPedido(r.getPedido().getIdPedido());
        dto.setEstado(r.getEstado());
        dto.setFecha(r.getFecha());
        return dto;
    }
}
