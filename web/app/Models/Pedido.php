<?php


namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Pedido extends Model
{
    protected $table = 'pedidos';
    protected $primaryKey = 'id_pedido';
    public $timestamps = false;

    protected $fillable = ['id_usuario', 'total', 'estado'];

    // Relación: Un pedido tiene muchos items
    public function items()
    {
        return $this->hasMany(PedidoItem::class, 'id_pedido', 'id_pedido');
    }
}