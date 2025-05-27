package com.gamestore.controller;

import com.gamestore.dto.CarritoDTO;
import com.gamestore.entity.Carrito;
import com.gamestore.mapper.CarritoMapper;
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
    public List<CarritoDTO> getAllCarritos() {
        return carritoService.getAllCarritos().stream()
                .map(CarritoMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public Carrito getCarritoById(@PathVariable Integer id) {
        return carritoService.getCarritoById(id).orElse(null);
    }

    @PostMapping
    public CarritoDTO createCarrito(@RequestBody Carrito carrito) {
        Carrito creado = carritoService.saveCarrito(carrito);
        return CarritoMapper.toDTO(creado);
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
