<!DOCTYPE html>
<html>
<head>
    <title>Tienda de Videojuegos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">Catálogo de Videojuegos</h1>
    <div class="row">
        @foreach ($videojuegos as $videojuego)
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <img src="{{ $videojuego->imagen_url }}" class="card-img-top" alt="{{ $videojuego->titulo }}">
                    <div class="card-body">
                        <h5 class="card-title">{{ $videojuego->titulo }}</h5>
                        <p class="card-text">{{ $videojuego->descripcion }}</p>
                        <p class="text-muted">Plataforma: {{ $videojuego->plataforma }}</p>
                        <p><strong>${{ $videojuego->precio }}</strong></p>
                        <a href="#" class="btn btn-primary">Agregar al carrito</a>
                    </div>
                </div>
            </div>
        @endforeach
    </div>
</div>
</body>
</html>
