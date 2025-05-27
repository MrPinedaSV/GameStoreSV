package com.gamestore.controller;

import com.gamestore.entity.Pedido;
import com.gamestore.entity.Rastreo_Pedidos;
import com.gamestore.dto.RastreoPedidoDTO;
import com.gamestore.repository.PedidoRepository;
import com.gamestore.service.RastreoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rastreo")
public class RastreoPedidoController {

    @Autowired
    private RastreoPedidoService rastreoPedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Rastreo_Pedidos> getAll() {
        return rastreoPedidoService.getAllRastreos();
    }

    @GetMapping("/{id}")
    public Rastreo_Pedidos getById(@PathVariable Integer id) {
        return rastreoPedidoService.getRastreoById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RastreoPedidoDTO dto) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(dto.getIdPedido());
        if (pedidoOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Pedido no encontrado");
        }

        Pedido pedido = pedidoOptional.get();

        // Crear nueva entrada de rastreo
        Rastreo_Pedidos rastreo = new Rastreo_Pedidos();
        rastreo.setEstado(dto.getEstado());
        rastreo.setFecha(LocalDateTime.now());
        rastreo.setPedido(pedido);
        rastreoPedidoService.saveRastreo(rastreo);

        // Actualizar estado actual del pedido
        pedido.setEstado(dto.getEstado());
        pedidoRepository.save(pedido);

        return ResponseEntity.ok("Estado actualizado correctamente");
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
