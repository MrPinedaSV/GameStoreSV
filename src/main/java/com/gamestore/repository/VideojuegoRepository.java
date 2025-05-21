package com.gamestore.repository;


import com.gamestore.entity.VideoJuego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideojuegoRepository extends JpaRepository<VideoJuego, Integer> {
}
