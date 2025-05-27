package com.gamestore.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO {
    private Integer idPedido;
    private Integer IdUsuario;
    private Double total;
    private String estado;
    private LocalDateTime fecha;
    private List<PedidoItemDTO> items;
    private List<RastreoPedidoDTO> rastreo;
}
