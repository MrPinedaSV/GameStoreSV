package com.tiendajuegos.service;



import com.tiendajuegos.model.PedidoDetalle;
import java.util.List;

public interface PedidoDetalleService {
    PedidoDetalle guardar(PedidoDetalle p);
    List<PedidoDetalle> listar();
    PedidoDetalle obtenerPorId(Long id);
    void eliminar(Long id);
    PedidoDetalle actualizar(Long id, PedidoDetalle p);
}

