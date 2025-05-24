package com.tiendajuegos.service;



import com.tiendajuegos.model.Videojuego;
import java.util.List;

public interface VideojuegoService {
    Videojuego guardar(Videojuego v);
    List<Videojuego> listar();
    Videojuego obtenerPorId(Long id);
    void eliminar(Long id);
    Videojuego actualizar(Long id, Videojuego v);
}

