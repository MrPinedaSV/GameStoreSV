package com.gamestore.controller;

import com.gamestore.entity.VideoJuego;
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
    public List<VideoJuego> getAll() {
        return videojuegoService.getAllVideojuegos();
    }

    @GetMapping("/{id}")
    public VideoJuego getById(@PathVariable Integer id) {
        return videojuegoService.getVideojuegoById(id).orElse(null);
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Va a buscar home.html en /templates
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
