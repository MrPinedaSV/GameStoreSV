<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Videojuego extends Model
{
    protected $table = 'videojuegos';
    protected $primaryKey = 'id_videojuego';
    public $timestamps = false;

    protected $fillable = ['titulo', 'descripcion', 'plataforma', 'precio', 'stock', 'imagen_url'];
}
