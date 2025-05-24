package com.tiendajuegos.service;

import com.tiendajuegos.model.Pago;
import java.util.List;

public interface PagoService {
    Pago guardar(Pago p);
    List<Pago> listar();
    Pago obtenerPorId(Long id);
    void eliminar(Long id);
    Pago actualizar(Long id, Pago p);
}
