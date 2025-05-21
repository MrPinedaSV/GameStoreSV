package com.gamestore.controller;

import com.gamestore.entity.Rastreo_Pedidos;
import com.gamestore.service.RastreoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rastreo")
public class RastreoPedidoController {
    @Autowired
    private RastreoPedidoService rastreoPedidoService;

    @GetMapping
    public List<Rastreo_Pedidos> getAll() {
        return rastreoPedidoService.getAllRastreos();
    }

    @GetMapping("/{id}")
    public Rastreo_Pedidos getById(@PathVariable Integer id) {
        return rastreoPedidoService.getRastreoById(id).orElse(null);
    }

    @PostMapping
    public Rastreo_Pedidos create(@RequestBody Rastreo_Pedidos rastreo) {
        return rastreoPedidoService.saveRastreo(rastreo);
    }

    @PutMapping("/{id}")
    public Rastreo_Pedidos update(@PathVariable Integer id, @RequestBody Rastreo_Pedidos rastreo) {
        rastreo.setIdRastreo(id);
        return rastreoPedidoService.saveRastreo(rastreo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        rastreoPedidoService.deleteRastreo(id);
    }
}
