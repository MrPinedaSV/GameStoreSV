package com.tiendajuegos.service;



import com.tiendajuegos.model.PedidoDetalle;
import com.tiendajuegos.repository.PedidoDetalleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoDetalleServiceImpl implements PedidoDetalleService {

    private final PedidoDetalleRepository repo;

    public PedidoDetalleServiceImpl(PedidoDetalleRepository repo) {
        this.repo = repo;
    }

    @Override
    public PedidoDetalle guardar(PedidoDetalle p) {
        return repo.save(p);
    }

    @Override
    public List<PedidoDetalle> listar() {
        return repo.findAll();
    }

    @Override
    public PedidoDetalle obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public PedidoDetalle actualizar(Long id, PedidoDetalle p) {
        PedidoDetalle original = obtenerPorId(id);
        if (original != null) {
            original.setCantidad(p.getCantidad());
            original.setPrecioUnitario(p.getPrecioUnitario());
            original.setPedido(p.getPedido());
            original.setVideojuego(p.getVideojuego());
            return repo.save(original);
        }
        return null;
    }
}
