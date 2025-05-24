package com.tiendajuegos.controller;

import com.tiendajuegos.model.*;
import com.tiendajuegos.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class ApiController {

    private final VideojuegoService videojuegoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;
    private final PedidoDetalleService pedidoDetalleService;
    private final PagoService pagoService;
    private final CarritoService carritoService;
    private final CarritoItemService carritoItemService;

    public ApiController(
            VideojuegoService videojuegoService,
            UsuarioService usuarioService,
            PedidoService pedidoService,
            PedidoDetalleService pedidoDetalleService,
            PagoService pagoService,
            CarritoService carritoService,
            CarritoItemService carritoItemService
    ) {
        this.videojuegoService = videojuegoService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
        this.pedidoDetalleService = pedidoDetalleService;
        this.pagoService = pagoService;
        this.carritoService = carritoService;
        this.carritoItemService = carritoItemService;
    }

    // -----------------------------------
    // Endpoints para Videojuego
    // -----------------------------------
    @GetMapping("/videojuego")
    public List<Videojuego> listarVideojuegos() {
        return videojuegoService.listar();
    }

    @GetMapping("/videojuego/{id}")
    public Videojuego obtenerVideojuegoPorId(@PathVariable Long id) {
        return videojuegoService.obtenerPorId(id);
    }

    @PostMapping("/videojuego")
    public Videojuego guardarVideojuego(@RequestBody Videojuego videojuego) {
        return videojuegoService.guardar(videojuego);
    }

    @PutMapping("/videojuego/{id}")
    public Videojuego actualizarVideojuego(@PathVariable Long id, @RequestBody Videojuego videojuego) {
        return videojuegoService.actualizar(id, videojuego);
    }

    @DeleteMapping("/videojuego/{id}")
    public void eliminarVideojuego(@PathVariable Long id) {
        videojuegoService.eliminar(id);
    }

    // -----------------------------------
    // Endpoints para Usuario (similar)
    // -----------------------------------
    @GetMapping("/usuario")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listar();
    }

    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @PostMapping("/usuario")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/usuario/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    // -----------------------------------
    // Endpoints para Pedido (similar)
    // -----------------------------------
    @GetMapping("/pedido")
    public List<Pedido> listarPedidos() {
        return pedidoService.listar();
    }

    @GetMapping("/pedido/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id);
    }

    @PostMapping("/pedido")
    public Pedido guardarPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardar(pedido);
    }

    @PutMapping("/pedido/{id}")
    public Pedido actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.actualizar(id, pedido);
    }

    @DeleteMapping("/pedido/{id}")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminar(id);
    }

    // -----------------------------------
    // Endpoints para PedidoDetalle (similar)
    // -----------------------------------
    @GetMapping("/pedidoDetalle")
    public List<PedidoDetalle> listarPedidoDetalles() {
        return pedidoDetalleService.listar();
    }

    @GetMapping("/pedidoDetalle/{id}")
    public PedidoDetalle obtenerPedidoDetallePorId(@PathVariable Long id) {
        return pedidoDetalleService.obtenerPorId(id);
    }

    @PostMapping("/pedidoDetalle")
    public PedidoDetalle guardarPedidoDetalle(@RequestBody PedidoDetalle pedidoDetalle) {
        return pedidoDetalleService.guardar(pedidoDetalle);
    }

    @PutMapping("/pedidoDetalle/{id}")
    public PedidoDetalle actualizarPedidoDetalle(@PathVariable Long id, @RequestBody PedidoDetalle pedidoDetalle) {
        return pedidoDetalleService.actualizar(id, pedidoDetalle);
    }

    @DeleteMapping("/pedidoDetalle/{id}")
    public void eliminarPedidoDetalle(@PathVariable Long id) {
        pedidoDetalleService.eliminar(id);
    }

    // -----------------------------------
    // Endpoints para Pago (similar)
    // -----------------------------------
    @GetMapping("/pago")
    public List<Pago> listarPagos() {
        return pagoService.listar();
    }

    @GetMapping("/pago/{id}")
    public Pago obtenerPagoPorId(@PathVariable Long id) {
        return pagoService.obtenerPorId(id);
    }

    @PostMapping("/pago")
    public Pago guardarPago(@RequestBody Pago pago) {
        return pagoService.guardar(pago);
    }

    @PutMapping("/pago/{id}")
    public Pago actualizarPago(@PathVariable Long id, @RequestBody Pago pago) {
        return pagoService.actualizar(id, pago);
    }

    @DeleteMapping("/pago/{id}")
    public void eliminarPago(@PathVariable Long id) {
        pagoService.eliminar(id);
    }

    // -----------------------------------
    // Endpoints para Carrito (similar)
    // -----------------------------------
    @GetMapping("/carrito")
    public List<Carrito> listarCarritos() {
        return carritoService.listar();
    }

    @GetMapping("/carrito/{id}")
    public Carrito obtenerCarritoPorId(@PathVariable Long id) {
        return carritoService.obtenerPorId(id);
    }

    @PostMapping("/carrito")
    public Carrito guardarCarrito(@RequestBody Carrito carrito) {
        return carritoService.guardar(carrito);
    }

    @PutMapping("/carrito/{id}")
    public Carrito actualizarCarrito(@PathVariable Long id, @RequestBody Carrito carrito) {
        return carritoService.actualizar(id, carrito);
    }

    @DeleteMapping("/carrito/{id}")
    public void eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminar(id);
    }

    // -----------------------------------
    // Endpoints para CarritoItem (similar)
    // -----------------------------------
    @GetMapping("/carritoItem")
    public List<CarritoItem> listarCarritoItems() {
        return carritoItemService.listar();
    }

    @GetMapping("/carritoItem/{id}")
    public CarritoItem obtenerCarritoItemPorId(@PathVariable Long id) {
        return carritoItemService.obtenerPorId(id);
    }

    @PostMapping("/carritoItem")
    public CarritoItem guardarCarritoItem(@RequestBody CarritoItem carritoItem) {
        return carritoItemService.guardar(carritoItem);
    }

    @PutMapping("/carritoItem/{id}")
    public CarritoItem actualizarCarritoItem(@PathVariable Long id, @RequestBody CarritoItem carritoItem) {
        return carritoItemService.actualizar(id, carritoItem);
    }

    @DeleteMapping("/carritoItem/{id}")
    public void eliminarCarritoItem(@PathVariable Long id) {
        carritoItemService.eliminar(id);
    }
}
