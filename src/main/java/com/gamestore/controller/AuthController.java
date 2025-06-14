package com.gamestore.controller;

import com.gamestore.dto.AuthRequest;
import com.gamestore.dto.RegistroUsuarioDTO;
import com.gamestore.entity.Usuario;
import com.gamestore.security.JwtResponse;
import com.gamestore.security.JwtUtils;
import com.gamestore.security.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gamestore.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioDetailsService usuarioDetailsService;
    private final JwtUtils jwtUtils;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println("Login attempt: " + request.getEmail());
        // Autenticación del usuario
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        // Cargar detalles del usuario (necesario para el token)
        UserDetails userDetails = usuarioDetailsService.loadUserByUsername(request.getEmail());
        // Generar token JWT
        String token = jwtUtils.generateToken(userDetails);
        // Obtener el objeto Usuario (para extraer el rol)
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        // Devolver token y rol
        return ResponseEntity.ok(new JwtResponse(token,usuario.getRol()));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroUsuarioDTO request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("El correo ya está en uso");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol("CLIENTE"); // o "ADMIN" si decides registrar administradores

        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}
