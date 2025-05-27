package com.gamestore.repository;

import com.gamestore.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByUsuarioEmail(String email);  // Este es el que necesitas
    Optional<Carrito> findByUsuarioIdUsuarioAndActivoTrue(Integer idUsuario);
    List<Carrito> findByUsuarioIdUsuario(Integer idUsuario);
}
