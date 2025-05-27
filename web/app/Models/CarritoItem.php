<?php


namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class CarritoItem extends Model
{
    protected $table = 'carrito_items';
    protected $primaryKey = 'id_item';
    public $timestamps = false;

    protected $fillable = ['id_carrito', 'id_videojuego', 'cantidad'];

    public function carrito()
    {
        return $this->belongsTo(Carrito::class, 'id_carrito');
    }

    public function videojuego()
    {
        return $this->belongsTo(Videojuego::class, 'id_videojuego', 'id_videojuego');
    }
}