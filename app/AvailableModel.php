<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class AvailableModel extends Model
{

    protected $table= 'available';
    public $timestamps = false;

    public function team_promotion()
    {
        return $this->belongsTo('App\TeamPromotionModel', 'no_prm', 'no_prm');

    }
}
