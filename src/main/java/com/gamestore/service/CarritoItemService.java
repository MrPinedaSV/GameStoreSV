package com.gamestore.service;

import com.gamestore.entity.Carrito;
import com.gamestore.entity.Carrito_Items;
import com.gamestore.entity.Usuario;
import com.gamestore.entity.VideoJuego;
import com.gamestore.repository.CarritoItemRepository;
import com.gamestore.repository.CarritoRepository;
import com.gamestore.repository.UsuarioRepository;
import com.gamestore.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoItemService {

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    public List<Carrito_Items> getAllItems() {
        return carritoItemRepository.findAll();
    }

    public Optional<Carrito_Items> getItemById(Integer id) {
        return carritoItemRepository.findById(id);
    }

    public Carrito_Items saveItem(Carrito_Items item, String emailUsuario) {
        // Buscar el usuario
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario con email " + emailUsuario + " no encontrado"));

        // Buscar carrito ACTIVO del usuario
        Carrito carrito = carritoRepository
                .findByUsuarioIdUsuarioAndActivoTrue(usuario.getIdUsuario())
                .orElse(null);

        // Si no hay carrito activo, crearlo
        if (carrito == null) {
            carrito = new Carrito();
            carrito.setUsuario(usuario);
            carrito.setFechaCreacion(LocalDateTime.now());
            carrito.setActivo(true);
            carrito = carritoRepository.save(carrito);
        }

        Integer idVideojuego = item.getVideojuego().getIdVideojuego();
        VideoJuego videojuego = videojuegoRepository.findById(idVideojuego)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));

        // Verificar si ya existe un ítem para ese videojuego en este carrito
        List<Carrito_Items> itemsExistentes = carritoItemRepository.findByCarritoIdCarrito(carrito.getIdCarrito());

        for (Carrito_Items existente : itemsExistentes) {
            if (existente.getVideojuego().getIdVideojuego().equals(idVideojuego)) {
                // Si ya existe, actualizar cantidad
                existente.setCantidad(existente.getCantidad() + item.getCantidad());
                return carritoItemRepository.save(existente);
            }
        }

        // Si no existe, guardar nuevo ítem
        item.setCarrito(carrito);
        item.setVideojuego(videojuego);
        return carritoItemRepository.save(item);
    }

    public Carrito_Items saveItem(Carrito_Items item) {
        // Método sobrecargado utilizado para actualizar ítems existentes (por ejemplo, cantidad)
        return carritoItemRepository.save(item);
    }

    public void deleteItem(Integer id) {
        carritoItemRepository.deleteById(id);
    }
}
