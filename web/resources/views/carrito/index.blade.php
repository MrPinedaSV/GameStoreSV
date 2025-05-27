@extends('layouts.app')

@section('content')
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-xl-11 col-lg-12">
            <div class="bg-white rounded-4 shadow-lg p-5 border">
                <h2 class="mb-5 text-center text-primary fw-bold display-5">
                    🛒 Tu Carrito de Compras
                </h2>

                @if(session('success'))
                    <div class="alert alert-success text-center fs-4 mb-4">
                        {{ session('success') }}
                    </div>
                @endif
                @if(session('error'))
                    <div class="alert alert-danger text-center fs-4 mb-4">
                        {{ session('error') }}
                    </div>
                @endif

                
@if($items->isEmpty())
    <div class="d-flex justify-content-center align-items-center" style="min-height: 350px;">
        <div class="alert alert-info text-center w-100 p-5 shadow-lg" style="font-size:2.2rem; border-radius:2rem;">
            <i class="bi bi-emoji-frown fs-1 d-block mb-3"></i>
            <span class="fw-bold">¡Tu carrito está vacío! 😢</span>
        </div>
    </div>
@else
                    <div class="table-responsive mb-5">
                        <table class="table table-bordered align-middle bg-light shadow rounded-4 border-primary" style="border-width:2.5px;">
                            <thead class="table-primary text-center align-middle">
                                <tr>
                                    <th style="min-width: 120px;">Imagen</th>
                                    <th style="min-width: 220px;">Título</th>
                                    <th style="min-width: 180px;">Descripción</th>
                                    <th style="min-width: 120px;">Plataforma</th>
                                    <th style="min-width: 100px;">Cantidad</th>
                                    <th style="min-width: 120px;">Precio</th>
                                    <th style="min-width: 120px;">Subtotal</th>
                                    <th style="min-width: 120px;">Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                @php $total = 0; @endphp
                                @foreach($items as $item)
                                    @php
                                        $subtotal = $item->cantidad * $item->videojuego->precio;
                                        $total += $subtotal;
                                    @endphp
                                    <tr>
                                        <td class="text-center">
                                            <img src="{{ $item->videojuego->imagen_url }}" alt="{{ $item->videojuego->titulo }}" class="rounded shadow-sm border border-primary" style="width: 100px; height: 100px; object-fit: cover;">
                                        </td>
                                        <td class="fw-bold fs-5 text-primary">{{ $item->videojuego->titulo }}</td>
                                        <td class="text-muted" style="max-width: 250px; white-space: normal;">{{ $item->videojuego->descripcion }}</td>
                                        <td class="text-secondary">{{ $item->videojuego->plataforma ?? '' }}</td>
                                        <td class="text-center">
                                            <span class="badge bg-info text-dark fs-5 px-3 py-2">{{ $item->cantidad }}</span>
                                        </td>
                                        <td class="text-end fw-semibold fs-5">${{ number_format($item->videojuego->precio, 2) }}</td>
                                        <td class="text-end fw-semibold fs-5">${{ number_format($subtotal, 2) }}</td>
                                        <td class="text-center">
                                            <form method="POST" action="{{ route('carrito.eliminar', $item->id_item) }}">
                                                @csrf
                                                @method('DELETE')
                                                <button class="btn btn-outline-danger btn-lg px-4 py-2" title="Quitar del carrito">
                                                    <i class="bi bi-trash"></i> Quitar
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                @endforeach
                                <tr class="bg-white text-primary fw-bold fs-4">
                                    <td colspan="6" class="text-end">Total:</td>
                                    <td colspan="2" class="text-end">${{ number_format($total, 2) }}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                @endif

                <div class="d-flex flex-column flex-md-row justify-content-between align-items-center mt-4 gap-3">
                    <a href="{{ url('/') }}" class="btn btn-outline-primary btn-lg px-5 py-3 fs-4 rounded-pill shadow-sm">
                        <i class="bi bi-arrow-left-circle me-2"></i> Seguir comprando
                    </a>
                    @if(!$items->isEmpty())
                        <form action="{{ route('carrito.pagar') }}" method="POST" class="d-inline">
                            @csrf
                            <button type="submit" class="btn btn-success btn-lg px-5 py-3 fs-4 rounded-pill shadow-sm">
                                <i class="bi bi-cash-coin me-2"></i> Proceder al pago
                            </button>
                        </form>
                    @endif
                </div>
            </div>
        </div>
    </div>
</div>

{{-- CSS personalizado --}}
<style>
    body {
        background: #f4f8fb !important;
        min-height: 100vh;
    }
    .table {
        border-radius: 1.2rem;
        overflow: hidden;
        margin-bottom: 0;
        border-width: 2.5px !important;
        border-color: #3498db !important;
        background: #f8fafc;
    }
    .table th, .table td {
        vertical-align: middle !important;
        padding-top: 1.3rem !important;
        padding-bottom: 1.3rem !important;
        font-size: 1.15rem;
        border-color: #3498db !important;
    }
    .table thead th {
        font-size: 1.18rem;
        letter-spacing: 1px;
        background: #eaf4fb !important;
        color: #1761a0 !important;
        border-color: #3498db !important;
    }
    .btn-outline-danger {
        border-color: #e74c3c;
        color: #e74c3c;
        font-size: 1.18rem;
        transition: 0.2s;
    }
    .btn-outline-danger:hover {
        background-color: #e74c3c;
        color: #fff;
    }
    .btn-outline-primary {
        border-color: #3498db;
        color: #3498db;
        font-size: 1.18rem;
        transition: 0.2s;
    }
    .btn-outline-primary:hover {
        background-color: #3498db;
        color: #fff;
    }
    .btn-success {
        background-color: #00b894;
        border: none;
        font-size: 1.18rem;
        transition: 0.2s;
    }
    .btn-success:hover {
        background-color: #00e0a0;
    }
    .rounded-pill {
        border-radius: 50rem !important;
    }
</style>
<!-- Si usas Bootstrap Icons, agrega esto en tu layout: -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
@endsection