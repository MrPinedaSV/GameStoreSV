package com.tiendajuegos.service;



import com.tiendajuegos.model.Pedido;
import com.tiendajuegos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repo;

    public PedidoServiceImpl(PedidoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Pedido guardar(Pedido p) {
        return repo.save(p);
    }

    @Override
    public List<Pedido> listar() {
        return repo.findAll();
    }

    @Override
    public Pedido obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Pedido actualizar(Long id, Pedido p) {
        Pedido original = obtenerPorId(id);
        if (original != null) {
            original.setFecha(p.getFecha());
            original.setTotal(p.getTotal());
            original.setEstado(p.getEstado());
            return repo.save(original);
        }
        return null;
    }
}

