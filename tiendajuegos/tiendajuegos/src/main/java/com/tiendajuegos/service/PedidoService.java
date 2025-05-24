package com.tiendajuegos.service;



import com.tiendajuegos.model.Pedido;
import java.util.List;

public interface PedidoService {
    Pedido guardar(Pedido p);
    List<Pedido> listar();
    Pedido obtenerPorId(Long id);
    void eliminar(Long id);
    Pedido actualizar(Long id, Pedido p);
}
