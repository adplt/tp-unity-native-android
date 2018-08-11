<?php

namespace App;

use Illuminate\Foundation\Auth\User;

class StaffModel extends User
{
    protected $table='staff';

    public function getRememberToken() {
        return $this->remember_token;
    }

    public function setRememberToken($value) {
        $this->remember_token = $value;
    }

    public function getRememberTokenName() {
        return 'remember_token';
    }

    /*public function event(){
        return $this->belongsTo(EventModel::class);
    }*/

}
