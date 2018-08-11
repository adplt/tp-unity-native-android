<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class TeamPromotionModel extends Model
{
    //
    protected $table = "team_promotion";
    public $timestamps = false;

    public function schedule()
    {

        return $this->hasOne('App\ScheduleModel', 'no_prm', 'no_prm');

    }

    public function absence()
    {

        return $this->hasOne('App\AbsenceModel', 'no_prm', 'no_prm');

    }

    public function available()
    {
        return $this->hasOne('App\AvailableModel', 'no_prm', 'no_prm');

    }
    public function degree()
    {
        return $this->hasMany('App\DegreeModel', 'id_degree', 'id');

    }


    /* public function student_candidate()
     {
         return $this->hasOne('App\StudentCandidateModel', 'no_prm', 'no_prm');

     }*/

  /*  public function event()
    {
        return $this->hasOne('App\EventModel','id_staff','id_staff');

    }*/
}
