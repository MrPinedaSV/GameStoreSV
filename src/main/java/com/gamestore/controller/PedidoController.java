package com.gamestore.controller;

import com.gamestore.dto.PedidoDTO;
import com.gamestore.entity.*;
import com.gamestore.mapper.PedidoMapper;
import com.gamestore.repository.UsuarioRepository;
import com.gamestore.service.PedidoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<PedidoDTO> getAllPedidos(Authentication authentication) {
        String email = authentication.getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Pedido> pedidos;

        if ("ADMIN".equalsIgnoreCase(usuario.getRol())) {
            pedidos = pedidoService.getAllPedidos();
        } else {
            pedidos = pedidoService.getPedidosPorUsuario(usuario.getIdUsuario());
        }

        return pedidos.stream()
                .map(PedidoMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Integer id) {
        return pedidoService.getPedidoById(id)
                .map(PedidoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody Pedido pedido) {
        Pedido creado = pedidoService.savePedido(pedido);
        return ResponseEntity.ok(PedidoMapper.toDTO(creado));
    }

    @PostMapping("/finalizar")
    public ResponseEntity<?> finalizarPedidoDesdeCarrito(Authentication authentication) {
        try {
            String emailUsuario = authentication.getName();
            PedidoDTO pedidoFinalizado = pedidoService.finalizarPedidoDesdeCarrito(emailUsuario);
            return ResponseEntity.ok(pedidoFinalizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Integer id) {
        pedidoService.deletePedido(id);
    }
}
