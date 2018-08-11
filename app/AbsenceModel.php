<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class AbsenceModel extends Model
{
    protected $table= 'absence';
    public $timestamps = false;

    public function team_promotion_schedule()
    {
        return $this->belongsTo('App\TeamPromotionModel', 'no_prm', 'no_prm');

    }
    public function team_promotion_absence()
    {
        return $this->belongsTo('App\TeamPromotionModel', 'no_prm', 'no_prm');

    }
    public function available()
    {
        return $this->hasMany('App\AvailableModel', 'id', 'id');
    }

}
