package com.tiendajuegos.service;



import com.tiendajuegos.model.Carrito;
import com.tiendajuegos.repository.CarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository repo;

    public CarritoServiceImpl(CarritoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Carrito guardar(Carrito c) {
        return repo.save(c);
    }

    @Override
    public List<Carrito> listar() {
        return repo.findAll();
    }

    @Override
    public Carrito obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Carrito actualizar(Long id, Carrito c) {
        Carrito original = obtenerPorId(id);
        if (original != null) {
            original.setCantidad(c.getCantidad());
            original.setUsuario(c.getUsuario());
            original.setVideojuego(c.getVideojuego());
            return repo.save(original);
        }
        return null;
    }
}

