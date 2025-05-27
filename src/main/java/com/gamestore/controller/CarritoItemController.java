package com.gamestore.controller;

import com.gamestore.dto.CarritoItemDTO;
import com.gamestore.entity.Carrito_Items;
import com.gamestore.mapper.CarritoItemMapper;
import com.gamestore.service.CarritoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrito-items")
public class CarritoItemController {

    @Autowired
    private CarritoItemService carritoItemService;

    // Obtener todos los items del carrito
    @GetMapping
    public List<CarritoItemDTO> getAllItems() {
        return carritoItemService.getAllItems()
                .stream()
                .map(CarritoItemMapper::toDTO)
                .toList();
    }

    // Obtener un item específico
    @GetMapping("/{id}")
    public ResponseEntity<CarritoItemDTO> getItemById(@PathVariable Integer id) {
        return carritoItemService.getItemById(id)
                .map(CarritoItemMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo ítem en el carrito (y crear el carrito si no existe)
    @PostMapping
    public ResponseEntity<CarritoItemDTO> createItem(@RequestBody Carrito_Items item, Authentication authentication) {
        String emailUsuario = authentication.getName();
        Carrito_Items creado = carritoItemService.saveItem(item, emailUsuario);
        URI location = URI.create("/api/carrito-items/" + creado.getIdItem());
        return ResponseEntity.created(location).body(CarritoItemMapper.toDTO(creado));
    }

    // Actualizar la cantidad de un ítem
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCantidad(@PathVariable Integer id, @RequestBody Map<String, Integer> body) {
        Integer nuevaCantidad = body.get("cantidad");

        Optional<Carrito_Items> optionalItem = carritoItemService.getItemById(id);
        if (optionalItem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Carrito_Items item = optionalItem.get();
        item.setCantidad(nuevaCantidad);
        carritoItemService.saveItem(item);

        return ResponseEntity.ok().build();
    }

    // Eliminar un ítem del carrito
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id) {
        carritoItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
