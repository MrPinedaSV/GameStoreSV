<?php


namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use App\Models\Carrito;
use App\Models\CarritoItem;
use App\Models\Pedido;
use App\Models\PedidoItem;
use App\Models\RastreoPedido;
use App\Models\Videojuego;

class CarritoController extends Controller
{
    // Agregar un videojuego al carrito
    public function agregar(Request $request)
    {
        $userId = Auth::id();
        $videojuegoId = $request->input('videojuego_id');

        // Obtener o crear carrito del usuario
        $carrito = Carrito::firstOrCreate(['id_usuario' => $userId]);

        // Verificar si el videojuego ya está en el carrito
        $item = CarritoItem::where('id_carrito', $carrito->id_carrito)
            ->where('id_videojuego', $videojuegoId)
            ->first();

        if ($item) {
            $item->cantidad += 1;
            $item->save();
        } else {
            CarritoItem::create([
                'id_carrito' => $carrito->id_carrito,
                'id_videojuego' => $videojuegoId,
                'cantidad' => 1,
            ]);
        }

        return redirect()->back()->with('success', 'Juego agregado al carrito');
    }

    // Mostrar el carrito
    public function mostrar()
    {
        $userId = Auth::id();

        $carrito = Carrito::where('id_usuario', $userId)->first();

        $items = $carrito
            ? $carrito->items()->with('videojuego')->get()
            : collect();

        return view('carrito.index', compact('items'));
    }

    // Eliminar un item del carrito
    public function eliminar($id)
    {
        $userId = Auth::id();

        $carrito = Carrito::where('id_usuario', $userId)->first();

        if (!$carrito) {
            return redirect()->back()->with('error', 'Carrito no encontrado');
        }

        $item = CarritoItem::where('id_item', $id)
            ->where('id_carrito', $carrito->id_carrito)
            ->first();

        if ($item) {
            $item->delete();
            return redirect()->back()->with('success', 'Item eliminado del carrito');
        }

        return redirect()->back()->with('error', 'Item no encontrado');
    }

    // Procesar el pago del carrito
    public function pagar(Request $request)
    {
        $userId = Auth::id();
        $carrito = Carrito::where('id_usuario', $userId)->first();

        if (!$carrito) {
            return redirect()->back()->with('error', 'No tienes carrito.');
        }

        $items = $carrito->items()->with('videojuego')->get();

        if ($items->isEmpty()) {
            return redirect()->back()->with('error', 'El carrito está vacío.');
        }

        // Calcular total
        $total = 0;
        foreach ($items as $item) {
            $total += $item->cantidad * $item->videojuego->precio;
        }

        // Crear pedido
        $pedido = Pedido::create([
            'id_usuario' => $userId,
            'total' => $total,
            'estado' => 'Pedido recibido',
        ]);

        // Crear items del pedido y actualizar stock
        foreach ($items as $item) {
            PedidoItem::create([
                'id_pedido' => $pedido->id_pedido,
                'id_videojuego' => $item->id_videojuego,
                'cantidad' => $item->cantidad,
                'precio_unitario' => $item->videojuego->precio,
            ]);

            // Actualizar stock
            $videojuego = $item->videojuego;
            $videojuego->stock -= $item->cantidad;
            $videojuego->save();
        }

        // Crear rastreo
        RastreoPedido::create([
            'id_pedido' => $pedido->id_pedido,
            'estado' => 'Pedido recibido',
        ]);

        // Vaciar carrito
        $carrito->items()->delete();

        // Mostrar confirmación
        return view('carrito.confirmacion', compact('pedido'));
    }
}