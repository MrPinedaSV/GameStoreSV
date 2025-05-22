package com.gamestore.controller;

import com.gamestore.entity.Pedido;
import com.gamestore.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/home")
    public String home() {
        return "home"; // Va a buscar home.html en /templates//sthephany
    }

    @GetMapping
    public List<Pedido> getAll() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public Pedido getById(@PathVariable Integer id) {
        return pedidoService.getPedidoById(id).orElse(null);
    }

    @PostMapping
    public Pedido create(@RequestBody Pedido pedido) {
        return pedidoService.savePedido(pedido);
    }

    @PutMapping("/{id}")
    public Pedido update(@PathVariable Integer id, @RequestBody Pedido pedido) {
        pedido.setIdPedido(id);
        return pedidoService.savePedido(pedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pedidoService.deletePedido(id);
    }
}
