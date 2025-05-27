@extends('layouts.app')

@section('content')
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-11">
            <div class="mx-auto bg-white rounded-4 shadow-lg p-5 border border-success border-2" style="max-width: 1000px;">
                <div class="text-center mb-5">
                    <i class="bi bi-bag-check-fill text-success" style="font-size: 4rem;"></i>
                    <h2 class="mt-3 mb-2 text-success fw-bold display-5">¡Gracias por tu compra!</h2>
                    <p class="fs-4 text-secondary">Tu pedido <span class="fw-bold text-dark">#{{ $pedido->id_pedido }}</span> ha sido recibido con éxito.</p>
                </div>

                <div class="table-responsive mb-4">
                    <table class="table table-bordered shadow-sm bg-light border-success rounded-4 overflow-hidden">
                        <thead class="table-success text-center fs-5">
                            <tr>
                                <th class="py-3">Producto</th>
                                <th class="py-3">Cantidad</th>
                                <th class="py-3">Precio unitario</th>
                                <th class="py-3">Subtotal</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($pedido->items as $item)
                                <tr>
                                    <td class="fw-semibold text-dark">
                                        {{ $item->videojuego->titulo }}<br>
                                        <span class="text-muted small">{{ $item->videojuego->plataforma ?? '' }}</span>
                                    </td>
                                    <td class="text-center fs-5">{{ $item->cantidad }}</td>
                                    <td class="text-end text-primary fs-5">${{ number_format($item->precio_unitario, 2) }}</td>
                                    <td class="text-end text-primary fs-5">${{ number_format($item->cantidad * $item->precio_unitario, 2) }}</td>
                                </tr>
                            @endforeach
                        </tbody>
                        <tfoot>
                            <tr class="bg-white text-success fw-bold fs-4">
                                <td colspan="3" class="text-end">Total pagado:</td>
                                <td class="text-end">${{ number_format($pedido->total, 2) }}</td>
                            </tr>
                        </tfoot>
                    </table>
                </div>

                <div class="text-center mt-4">
                    <a href="{{ url('/') }}" class="btn btn-outline-primary btn-lg px-5 py-3 rounded-pill shadow-sm fs-4">
                        <i class="bi bi-house-door me-2"></i> Volver a la tienda
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

{{-- CSS personalizado adicional --}}
<style>
    body {
        background-color: #f4f9f5;
    }
    .table {
        border-width: 2px !important;
    }
    .table th, .table td {
        vertical-align: middle !important;
        padding: 1.2rem 1rem !important;
    }
    .table thead th {
        background-color: #eafaf1 !important;
        color: #176e43 !important;
    }
</style>
<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
@endsection
