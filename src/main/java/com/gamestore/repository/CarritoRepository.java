package com.gamestore.repository;

import com.gamestore.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    List<Carrito> findByUsuarioIdUsuario(Integer idUsuario);
}
