package com.tiendajuegos.service;

import com.tiendajuegos.model.CarritoItem;
import com.tiendajuegos.repository.CarritoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoItemServiceImpl implements CarritoItemService {

    private final CarritoItemRepository repo;

    public CarritoItemServiceImpl(CarritoItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public CarritoItem guardar(CarritoItem item) {
        return repo.save(item);
    }

    @Override
    public List<CarritoItem> listar() {
        return repo.findAll();
    }

    @Override
    public CarritoItem obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public CarritoItem actualizar(Long id, CarritoItem item) {
        CarritoItem original = obtenerPorId(id);
        if (original != null) {
            original.setCantidad(item.getCantidad());
            original.setCarrito(item.getCarrito());
            original.setVideojuego(item.getVideojuego());
            return repo.save(original);
        }
        return null;
    }
}
