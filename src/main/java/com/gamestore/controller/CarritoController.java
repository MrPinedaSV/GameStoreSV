package com.gamestore.controller;

import com.gamestore.entity.Carrito;
import com.gamestore.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> getAllCarritos() {
        return carritoService.getAllCarritos();
    }

    @GetMapping("/{id}")
    public Carrito getCarritoById(@PathVariable Integer id) {
        return carritoService.getCarritoById(id).orElse(null);
    }

    @PostMapping
    public Carrito createCarrito(@RequestBody Carrito carrito) {
        return carritoService.saveCarrito(carrito);
    }

    @PutMapping("/{id}")
    public Carrito updateCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        carrito.setIdCarrito(id);
        return carritoService.saveCarrito(carrito);
    }

    @DeleteMapping("/{id}")
    public void deleteCarrito(@PathVariable Integer id) {
        carritoService.deleteCarrito(id);
    }
}
