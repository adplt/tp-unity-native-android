<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class SupportModel extends Model
{
    protected $table= 'support';
    public $timestamps = false;

    public function schedule()
    {
        return $this->hasOne('App\SupportModel', 'no_prm', 'no_prm');

    }


}
