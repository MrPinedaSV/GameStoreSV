<?php


use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\VideojuegoController;
use App\Http\Controllers\CarritoController;

// Rutas protegidas para el carrito
Route::middleware('auth')->group(function () {
    Route::post('/carrito/agregar', [CarritoController::class, 'agregar'])->name('carrito.agregar');
    Route::get('/carrito', [CarritoController::class, 'mostrar'])->name('carrito.mostrar');
    Route::delete('/carrito/eliminar/{id}', [CarritoController::class, 'eliminar'])->name('carrito.eliminar');
    Route::post('/carrito/pagar', [CarritoController::class, 'pagar'])->name('carrito.pagar');
    Route::get('/inicio', [VideojuegoController::class, 'index'])->name('inicio');
});

// Página raíz: redirige a login o a inicio si está autenticado
Route::get('/', function () {
    return auth()->check() ? redirect()->route('inicio') : redirect()->route('login');
});

// Formulario login
Route::get('/login', [AuthController::class, 'showLoginForm'])->name('login');
Route::post('/login', [AuthController::class, 'login'])->name('login.post');

// Formulario registro
Route::get('/register', [AuthController::class, 'showRegisterForm'])->name('register');
Route::post('/register', [AuthController::class, 'register'])->name('register.post');

// Logout
Route::post('/logout', [AuthController::class, 'logout'])->name('logout');