package com.gamestore.repository;

import com.gamestore.entity.Carrito_Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoItemRepository extends JpaRepository<Carrito_Items, Integer> {
    List<Carrito_Items> findByCarritoId(Integer idCarrito);
}
