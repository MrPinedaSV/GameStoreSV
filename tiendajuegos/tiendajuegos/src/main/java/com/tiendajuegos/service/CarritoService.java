package com.tiendajuegos.service;



import com.tiendajuegos.model.Carrito;
import java.util.List;

public interface CarritoService {
    Carrito guardar(Carrito c);
    List<Carrito> listar();
    Carrito obtenerPorId(Long id);
    void eliminar(Long id);
    Carrito actualizar(Long id, Carrito c);
}

