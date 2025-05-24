package com.tiendajuegos.service;



import com.tiendajuegos.model.Videojuego;
import com.tiendajuegos.repository.VideojuegoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoServiceImpl implements VideojuegoService {

    private final VideojuegoRepository repo;

    public VideojuegoServiceImpl(VideojuegoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Videojuego guardar(Videojuego v) {
        return repo.save(v);
    }

    @Override
    public List<Videojuego> listar() {
        return repo.findAll();
    }

    @Override
    public Videojuego obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Videojuego actualizar(Long id, Videojuego v) {
        Videojuego original = obtenerPorId(id);
        if (original != null) {
            original.setTitulo(v.getTitulo());
            original.setDescripcion(v.getDescripcion());
            original.setPrecio(v.getPrecio());
            original.setStock(v.getStock());

            return repo.save(original);
        }
        return null;
    }
}
