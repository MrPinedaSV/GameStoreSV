<?php


namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Carrito extends Model
{
    protected $table = 'carritos';
    protected $primaryKey = 'id_carrito';
    public $timestamps = false;

    protected $fillable = ['id_usuario'];

    public function items()
    {
        return $this->hasMany(CarritoItem::class, 'id_carrito', 'id_carrito');
    }

    public function usuario()
    {
        // Cambia 'User' por 'Usuario' si tu modelo de usuarios se llama Usuario
        return $this->belongsTo(User::class, 'id_usuario', 'id_usuario');
    }
}