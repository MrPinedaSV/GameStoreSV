package com.gamestore.controller;

import com.gamestore.entity.Carrito_Items;
import com.gamestore.service.CarritoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito-items")
public class CarritoItemController {
    @Autowired
    private CarritoItemService carritoItemService;

    @GetMapping
    public List<Carrito_Items> getAllItems() {
        return carritoItemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Carrito_Items getItemById(@PathVariable Integer id) {
        return carritoItemService.getItemById(id).orElse(null);
    }

    @PostMapping
    public Carrito_Items createItem(@RequestBody Carrito_Items item) {
        return carritoItemService.saveItem(item);
    }

    @PutMapping("/{id}")
    public Carrito_Items updateItem(@PathVariable Integer id, @RequestBody Carrito_Items item) {
        item.setIdItem(id);
        return carritoItemService.saveItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id) {
        carritoItemService.deleteItem(id);
    }
}
