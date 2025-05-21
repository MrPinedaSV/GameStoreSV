package com.gamestore.service;

import com.gamestore.entity.VideoJuego;
import com.gamestore.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoJuegoService {
    @Autowired
    private VideojuegoRepository videojuegoRepository;

    public List<VideoJuego> getAllVideojuegos() {
        return videojuegoRepository.findAll();
    }

    public Optional<VideoJuego> getVideojuegoById(Integer id) {
        return videojuegoRepository.findById(id);
    }

    public VideoJuego saveVideojuego(VideoJuego videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    public void deleteVideojuego(Integer id) {
        videojuegoRepository.deleteById(id);
    }
}
