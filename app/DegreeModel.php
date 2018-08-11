<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class DegreeModel extends Model
{
    protected $table= 'degree';
    public $timestamps = false;

    public function degree()
    {
        return $this->belongsTo('App\DegreeModel', 'id_degree', 'id');

    }

}
