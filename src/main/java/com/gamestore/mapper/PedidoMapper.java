package com.gamestore.mapper;

import com.gamestore.dto.PedidoDTO;
import com.gamestore.entity.Pedido;
import com.gamestore.dto.PedidoItemDTO;

import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(pedido.getIdPedido());
        dto.setEstado(pedido.getEstado());
        dto.setFecha(pedido.getFecha());
        dto.setTotal(pedido.getTotal());
        dto.setIdUsuario(pedido.getUsuario().getIdUsuario());

        if (pedido.getItems() != null) {
            dto.setItems(pedido.getItems().stream()
                    .map(PedidoItemMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        // Si también mapeas rastreo
        if (pedido.getRastreo() != null) {
            dto.setRastreo(pedido.getRastreo().stream()
                    .map(RastreoPedidoMapper::toDTO) // si tienes este mapper
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
