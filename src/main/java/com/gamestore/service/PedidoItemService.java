package com.gamestore.service;

import com.gamestore.entity.Pedido;
import com.gamestore.entity.Pedido_Items;
import com.gamestore.entity.VideoJuego;
import com.gamestore.repository.PedidoItemRepository;
import com.gamestore.repository.PedidoRepository;
import com.gamestore.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    public List<Pedido_Items> getAllItems() {
        return pedidoItemRepository.findAll();
    }

    public Optional<Pedido_Items> getItemById(Integer id) {
        return pedidoItemRepository.findById(id);
    }

    public Pedido_Items saveItem(Pedido_Items item) {
        // Cargar pedido y videojuego completos
        Pedido pedido = pedidoRepository.findById(item.getPedido().getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        VideoJuego videojuego = videojuegoRepository.findById(item.getVideojuego().getIdVideojuego())
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));

        // Asignar entidades completas
        item.setPedido(pedido);
        item.setVideojuego(videojuego);

        // Guardar el item
        Pedido_Items creado = pedidoItemRepository.save(item);

        // Calcular subtotal y actualizar el total del pedido
        double subtotal = item.getCantidad() * item.getPrecioUnitario();
        pedido.setTotal(pedido.getTotal() + subtotal);
        pedidoRepository.save(pedido);

        return creado;
    }

    public void deleteItem(Integer id) {
        pedidoItemRepository.deleteById(id);
    }
}
