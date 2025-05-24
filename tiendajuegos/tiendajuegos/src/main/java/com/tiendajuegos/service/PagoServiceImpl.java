package com.tiendajuegos.service;

import com.tiendajuegos.model.Pago;
import com.tiendajuegos.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository repo;

    public PagoServiceImpl(PagoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Pago guardar(Pago p) {
        return repo.save(p);
    }

    @Override
    public List<Pago> listar() {
        return repo.findAll();
    }

    @Override
    public Pago obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Pago actualizar(Long id, Pago p) {
        Pago original = obtenerPorId(id);
        if (original != null) {
            original.setMonto(p.getMonto());
            original.setFecha(p.getFecha());
            original.setMetodo(p.getMetodo());
            original.setPedido(p.getPedido());
            return repo.save(original);
        }
        return null;
    }
}
