<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class RastreoPedido extends Model
{
    protected $table = 'rastreo_pedidos';
    protected $primaryKey = 'id_rastreo';
    public $timestamps = false;

    protected $fillable = ['id_pedido', 'estado'];
}