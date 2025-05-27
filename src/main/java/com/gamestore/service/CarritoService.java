package com.gamestore.service;

import com.gamestore.entity.Carrito;
import com.gamestore.entity.Usuario;
import com.gamestore.repository.CarritoRepository;
import com.gamestore.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Carrito> getAllCarritos() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> getCarritoById(Integer id) {
        return carritoRepository.findById(id);
    }

    public Carrito saveCarrito(Carrito carrito) {
        Integer idUsuario = carrito.getUsuario().getIdUsuario();
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow();
        carrito.setUsuario(usuario);
        return carritoRepository.save(carrito);
    }

    public void deleteCarrito(Integer id) {
        carritoRepository.deleteById(id);
    }
}
