package com.gamestore.mapper;

import com.gamestore.dto.CarritoDTO;
import com.gamestore.entity.Carrito;

public class CarritoMapper {
    public static CarritoDTO toDTO(Carrito carrito) {
        CarritoDTO dto = new CarritoDTO();
        dto.setIdCarrito(carrito.getIdCarrito());
        dto.setIdUsuario(carrito.getUsuario().getIdUsuario());
        dto.setFechaCreacion(carrito.getFechaCreacion());
        return dto;
    }
}
