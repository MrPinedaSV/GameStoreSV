package com.gamestore.mapper;

import com.gamestore.dto.RastreoPedidoDTO;
import com.gamestore.entity.Rastreo_Pedidos;

public class RastreoPedidoMapper {
    public static RastreoPedidoDTO toDTO(Rastreo_Pedidos entity) {
        RastreoPedidoDTO dto = new RastreoPedidoDTO();
        dto.setIdRastreo(entity.getIdRastreo());
        dto.setEstado(entity.getEstado());
        dto.setFecha(entity.getFecha());

        if (entity.getPedido() != null) {
            dto.setIdPedido(entity.getPedido().getIdPedido());
        }

        return dto;
    }

    public static Rastreo_Pedidos toEntity(RastreoPedidoDTO dto) {
        Rastreo_Pedidos entity = new Rastreo_Pedidos();
        entity.setIdRastreo(dto.getIdRastreo());
        entity.setEstado(dto.getEstado());
        entity.setFecha(dto.getFecha());
        // NOTA: No se asigna el Pedido completo aquí, porque se requiere su entidad completa.
        return entity;
    }
}
