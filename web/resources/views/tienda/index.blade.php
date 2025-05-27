<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Tienda de Videojuegos - Estilo Steam</title>
    <!-- Google Fonts: Roboto -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet" />
    <style>
        /* Reset y tipografía */
        * {
            box-sizing: border-box;
        }
        body {
            margin: 0;
            font-family: 'Roboto', sans-serif;
            background: #121212;
            color: #ddd;
        }

        header {
            background-color: #1b1b1b;
            padding: 20px 40px;
            text-align: center;
            box-shadow: 0 2px 8px rgba(0,0,0,0.8);
            font-size: 2rem;
            font-weight: 700;
            color: #66c0f4;
            letter-spacing: 2px;
            user-select: none;
            position: relative;
        }

        .carrito-link {
            position: absolute;
            right: 40px;
            top: 50%;
            transform: translateY(-50%);
            color: #fff;
            background: #0984e3;
            padding: 8px 16px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 1rem;
            transition: background 0.2s;
        }
        .carrito-link:hover {
            background: #74b9ff;
            color: #222;
        }

        .container {
            max-width: 1200px;
            margin: 40px auto;
            padding: 0 20px;
        }

        .grid-videojuegos {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 25px;
        }

        .card {
            background: #1e1e1e;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.7);
            overflow: hidden;
            color: #ccc;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            cursor: pointer;
            display: flex;
            flex-direction: column;
        }

        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 10px 30px rgba(0, 192, 255, 0.7);
        }

        .card img {
            width: 100%;
            height: 140px;
            object-fit: cover;
            filter: brightness(0.85);
            transition: filter 0.3s ease;
        }

        .card:hover img {
            filter: brightness(1);
        }

        .card-body {
            padding: 15px;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .card-title {
            font-weight: 700;
            font-size: 1.25rem;
            margin-bottom: 8px;
            color: #66c0f4;
        }

        .card-text {
            font-size: 0.9rem;
            margin-bottom: 12px;
            flex-grow: 1;
            color: #bbb;
        }

        .platform {
            font-size: 0.85rem;
            font-style: italic;
            color: #7f8c8d;
            margin-bottom: 12px;
        }

        .price {
            font-weight: 700;
            font-size: 1.1rem;
            color: #00b894;
            margin-bottom: 15px;
        }

        .btn {
            background-color: #0984e3;
            border: none;
            padding: 10px 0;
            text-align: center;
            font-weight: 600;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            user-select: none;
            transition: background-color 0.25s ease;
        }

        .btn:hover {
            background-color: #74b9ff;
        }

        /* Scrollbar para la página */
        ::-webkit-scrollbar {
            width: 10px;
        }
        ::-webkit-scrollbar-track {
            background: #1e1e1e;
        }
        ::-webkit-scrollbar-thumb {
            background-color: #0984e3;
            border-radius: 10px;
            border: 2px solid #1e1e1e;
        }
    </style>
</head>
<body>
    <header>
        TIENDA DE VIDEOJUEGOS
        <a href="{{ route('carrito.mostrar') }}" class="carrito-link">
            🛒 Ver Carrito
        </a>
    </header>
    <div class="container">
        <div class="grid-videojuegos">
            @foreach ($videojuegos as $videojuego)
                <div class="card">
                    <img src="{{ $videojuego->imagen_url }}" alt="{{ $videojuego->titulo }}" />
                    <div class="card-body">
                        <h5 class="card-title">{{ $videojuego->titulo }}</h5>
                        <p class="card-text">{{ $videojuego->descripcion }}</p>
                        <p class="platform">Plataforma: {{ $videojuego->plataforma }}</p>
                        <p class="price">${{ number_format($videojuego->precio, 2) }}</p>
                        <form method="POST" action="{{ route('carrito.agregar') }}">
                            @csrf
                            <input type="hidden" name="videojuego_id" value="{{ $videojuego->id_videojuego }}">
                            <button type="submit" class="btn">Agregar al carrito</button>
                        </form>
                    </div>
                </div>
            @endforeach
        </div>
    </div>
</body>
</html>