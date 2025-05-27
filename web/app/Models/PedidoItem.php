<?php
// filepath: c:\Users\William\Desktop\test\web\app\Models\PedidoItem.php


namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class PedidoItem extends Model
{
    protected $table = 'pedido_items';
    protected $primaryKey = 'id_item';
    public $timestamps = false;

    protected $fillable = ['id_pedido', 'id_videojuego', 'cantidad', 'precio_unitario'];

    // Relación: Un item pertenece a un videojuego
    public function videojuego()
    {
        return $this->belongsTo(Videojuego::class, 'id_videojuego', 'id_videojuego');
    }
}