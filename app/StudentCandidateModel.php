<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class StudentCandidateModel extends Model
{
    protected $table= 'student_candidate';
    public $timestamps = false;


    public function team_promotion()
    {
        return $this->belongsTo('App\TeamPromotionModel', 'no_prm', 'no_prm');

    }
}
