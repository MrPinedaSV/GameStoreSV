package com.gamestore.service;

import com.gamestore.entity.Carrito_Items;
import com.gamestore.repository.CarritoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoItemService {
    @Autowired
    private CarritoItemRepository carritoItemRepository;

    public List<Carrito_Items> getAllItems() {
        return carritoItemRepository.findAll();
    }

    public Optional<Carrito_Items> getItemById(Integer id) {
        return carritoItemRepository.findById(id);
    }

    public Carrito_Items saveItem(Carrito_Items item) {
        return carritoItemRepository.save(item);
    }

    public void deleteItem(Integer id) {
        carritoItemRepository.deleteById(id);
    }
}
