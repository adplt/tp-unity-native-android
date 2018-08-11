<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class EventModel extends Model
{

    protected $table= 'event';


    //public $timestamps = false;

    /*public function staff()
    {
        return $this->hasOne(TeamPromotionModel::class, 'id_staff');
    }*/
}
