package com.gamestore.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CarritoDTO {
    private Integer idCarrito;
    private  Integer IdUsuario;
    private LocalDateTime fechaCreacion;
    private List<CarritoItemDTO> items;
}
