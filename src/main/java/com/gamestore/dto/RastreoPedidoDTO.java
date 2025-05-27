package com.gamestore.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RastreoPedidoDTO {
    private Integer idRastreo;
    private String estado;
    private LocalDateTime fecha;
    private Integer IdPedido;
}
