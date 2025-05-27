package com.gamestore.mapper;

import com.gamestore.dto.VideoJuegoDTO;
import com.gamestore.entity.VideoJuego;

public class VideoJuegoMapper {
    public static VideoJuegoDTO toDTO(VideoJuego videojuego) {
        VideoJuegoDTO dto = new VideoJuegoDTO();
        dto.setIdVideojuego(videojuego.getIdVideojuego());
        dto.setTitulo(videojuego.getTitulo());
        dto.setDescripcion(videojuego.getDescripcion());
        dto.setPlataforma(videojuego.getPlataforma());
        dto.setPrecio(videojuego.getPrecio());
        dto.setStock(videojuego.getStock());
        dto.setImagenUrl(videojuego.getImagenUrl());
        return dto;
    }

    public static VideoJuego toEntity(VideoJuegoDTO dto) {
        VideoJuego videojuego = new VideoJuego();
        videojuego.setIdVideojuego(dto.getIdVideojuego());
        videojuego.setTitulo(dto.getTitulo());
        videojuego.setDescripcion(dto.getDescripcion());
        videojuego.setPlataforma(dto.getPlataforma());
        videojuego.setPrecio(dto.getPrecio());
        videojuego.setStock(dto.getStock());
        videojuego.setImagenUrl(dto.getImagenUrl());
        return videojuego;
    }
}
