package com.tiendajuegos.service;




import com.tiendajuegos.model.Usuario;
import java.util.List;

public interface UsuarioService {
    Usuario guardar(Usuario u);
    List<Usuario> listar();
    Usuario obtenerPorId(Long id);
    void eliminar(Long id);
    Usuario actualizar(Long id, Usuario u);
}