package com.gamestore.controller;

import com.gamestore.dto.VideoJuegoDTO;
import com.gamestore.entity.VideoJuego;
import com.gamestore.mapper.VideoJuegoMapper;
import com.gamestore.service.VideoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {
    @Autowired
    private VideoJuegoService videojuegoService;

    @GetMapping
    public List<VideoJuegoDTO> getAll() {
        return videojuegoService.getAllVideojuegos()
                .stream()
                .map(VideoJuegoMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public VideoJuego getById(@PathVariable Integer id) {
        return videojuegoService.getVideojuegoById(id).orElse(null);
    }

    @PostMapping
    public VideoJuego create(@RequestBody VideoJuego videojuego) {
        return videojuegoService.saveVideojuego(videojuego);
    }

    @PutMapping("/{id}")
    public VideoJuego update(@PathVariable Integer id, @RequestBody VideoJuego videojuego) {
        videojuego.setIdVideojuego(id);
        return videojuegoService.saveVideojuego(videojuego);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        videojuegoService.deleteVideojuego(id);
    }
}
