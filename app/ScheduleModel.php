<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class ScheduleModel extends Model
{
    protected $table = 'schedule';
    public $timestamps = false;

    public function team_promotion()
    {
        return $this->belongsTo('App\TeamPromotionModel', 'no_prm', 'no_prm');

    }

    public function supports()
    {
        return $this->hasMany('App\SupportModel', 'id', 'id');
    }
}
