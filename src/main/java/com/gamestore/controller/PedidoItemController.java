package com.gamestore.controller;

import com.gamestore.dto.PedidoItemDTO;
import com.gamestore.entity.Pedido_Items;
import com.gamestore.mapper.PedidoItemMapper;
import com.gamestore.service.PedidoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido-items")
public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    @GetMapping
    public List<PedidoItemDTO> getAllItems() {
        return pedidoItemService.getAllItems().stream()
                .map(PedidoItemMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public Pedido_Items getById(@PathVariable Integer id) {
        return pedidoItemService.getItemById(id).orElse(null);
    }

    @PostMapping
    public PedidoItemDTO create(@RequestBody Pedido_Items item) {
        Pedido_Items creado = pedidoItemService.saveItem(item);
        return PedidoItemMapper.toDTO(creado);
    }

    @PutMapping("/{id}")
    public Pedido_Items update(@PathVariable Integer id, @RequestBody Pedido_Items item) {
        item.setIdItem(id);
        return pedidoItemService.saveItem(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pedidoItemService.deleteItem(id);
    }
}
