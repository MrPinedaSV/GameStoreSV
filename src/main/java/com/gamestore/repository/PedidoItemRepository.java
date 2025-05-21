package com.gamestore.repository;

import com.gamestore.entity.Pedido_Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoItemRepository extends JpaRepository<Pedido_Items, Integer> {
    List<Pedido_Items> findByPedidoId(Integer idPedido);
}
