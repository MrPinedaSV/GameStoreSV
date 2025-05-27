package com.gamestore.service;

import com.gamestore.dto.PedidoDTO;
import com.gamestore.entity.*;
import com.gamestore.mapper.PedidoMapper;
import com.gamestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RestreoPedidoRepository rastreoPedidoRepository;

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoById(Integer id) {
        return pedidoRepository.findById(id);
    }

    public Pedido savePedido(Pedido pedido) {
        Integer idUsuario = pedido.getUsuario().getIdUsuario();
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        pedido.setUsuario(usuario);
        pedido.setEstado("Pedido recibido");
        pedido.setTotal(0.0);
        pedido.setFecha(LocalDateTime.now());

        Pedido savedPedido = pedidoRepository.save(pedido);

        Rastreo_Pedidos rastreo = new Rastreo_Pedidos();
        rastreo.setPedido(savedPedido);
        rastreo.setEstado("Pedido recibido");
        rastreo.setFecha(LocalDateTime.now());

        rastreoPedidoRepository.save(rastreo);

        return savedPedido;
    }

    public PedidoDTO finalizarPedidoDesdeCarrito(String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar carrito activo del usuario
        Carrito carrito = carritoRepository
                .findByUsuarioIdUsuarioAndActivoTrue(usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("No tienes un carrito activo"));

        // Obtener ítems del carrito
        List<Carrito_Items> itemsCarrito = carritoItemRepository.findByCarritoIdCarrito(carrito.getIdCarrito());

        if (itemsCarrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        // Crear nuevo pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEstado("Pedido recibido");
        pedido.setFecha(LocalDateTime.now());
        pedido.setTotal(0.0);
        pedido.setItems(new ArrayList<>());

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        double total = 0.0;
        for (Carrito_Items itemCarrito : itemsCarrito) {
            Pedido_Items itemPedido = new Pedido_Items();
            itemPedido.setPedido(pedidoGuardado);
            itemPedido.setVideojuego(itemCarrito.getVideojuego());
            itemPedido.setCantidad(itemCarrito.getCantidad());
            itemPedido.setPrecioUnitario(itemCarrito.getVideojuego().getPrecio());

            total += itemPedido.getCantidad() * itemPedido.getPrecioUnitario();

            pedidoItemRepository.save(itemPedido);
        }

        pedidoGuardado.setTotal(total);
        pedidoRepository.save(pedidoGuardado);

        // Registrar en rastreo_pedidos
        Rastreo_Pedidos rastreo = new Rastreo_Pedidos();
        rastreo.setPedido(pedidoGuardado);
        rastreo.setEstado("Pedido recibido");
        rastreo.setFecha(LocalDateTime.now());
        rastreoPedidoRepository.save(rastreo);

        // Importante: marcar el carrito como inactivo
        carrito.setActivo(false);
        carritoRepository.save(carrito);

        // También puedes borrar los ítems si lo deseas
        carritoItemRepository.deleteAll(itemsCarrito);

        return PedidoMapper.toDTO(pedidoGuardado);
    }

    public List<Pedido> getPedidosPorUsuario(Integer idUsuario) {
        return pedidoRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public void deletePedido(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
