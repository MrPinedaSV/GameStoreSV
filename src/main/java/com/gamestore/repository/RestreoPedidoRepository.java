package com.gamestore.repository;

import com.gamestore.entity.Rastreo_Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestreoPedidoRepository extends JpaRepository<Rastreo_Pedidos, Integer> {
    List<Rastreo_Pedidos> findByPedidoId(Integer idPedido);
}
