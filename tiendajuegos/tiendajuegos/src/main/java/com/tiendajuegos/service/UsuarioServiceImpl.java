package com.tiendajuegos.service;




import com.tiendajuegos.model.Usuario;
import com.tiendajuegos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public Usuario guardar(Usuario u) {
        return repo.save(u);
    }

    @Override
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Usuario actualizar(Long id, Usuario u) {
        Usuario original = obtenerPorId(id);
        if (original != null) {
            original.setNombre(u.getNombre());
            original.setEmail(u.getEmail());
            original.setPassword(u.getPassword());
            // agrega más campos si tienes
            return repo.save(original);
        }
        return null;
    }
}
