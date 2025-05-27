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
        dto.setVideojuego(VideoJuegoMapper.toDTO(item.getVideojuego()));
        return dto;
    }

    public static Carrito_Items toEntity(CarritoItemDTO dto) {
        Carrito_Items item = new Carrito_Items();

        // Solo se asignan IDs, las entidades completas deben cargarse en el servicio
        item.setIdItem(dto.getIdItem());
        item.setCantidad(dto.getCantidad());

        return item;
    }
}
