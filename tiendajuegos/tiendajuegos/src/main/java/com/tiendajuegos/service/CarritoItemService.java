package com.tiendajuegos.service;

import com.tiendajuegos.model.CarritoItem;
import java.util.List;

public interface CarritoItemService {
    CarritoItem guardar(CarritoItem item);
    List<CarritoItem> listar();
    CarritoItem obtenerPorId(Long id);
    void eliminar(Long id);
    CarritoItem actualizar(Long id, CarritoItem item);
}
