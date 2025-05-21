package com.gamestore.service;

import com.gamestore.entity.Pedido_Items;
import com.gamestore.repository.PedidoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoItemService {
    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    public List<Pedido_Items> getAllItems() {
        return pedidoItemRepository.findAll();
    }

    public Optional<Pedido_Items> getItemById(Integer id) {
        return pedidoItemRepository.findById(id);
    }

    public Pedido_Items saveItem(Pedido_Items item) {
        return pedidoItemRepository.save(item);
    }

    public void deleteItem(Integer id) {
        pedidoItemRepository.deleteById(id);
    }
}
