package com.gamestore.mapper;

import com.gamestore.dto.CarritoItemDTO;
import com.gamestore.entity.Carrito_Items;

public class CarritoItemMapper {
    public static CarritoItemDTO toDTO(Carrito_Items item) {
        CarritoItemDTO dto = new CarritoItemDTO();
        dto.setIdItem(item.getIdItem());
        dto.setIdCarrito(item.getCarrito().getIdCarrito());
        dto.setIdVideojuego(item.getVideojuego().getIdVideojuego());
        dto.setCantidad(item.getCantidad());
        return dto;
    }
}
