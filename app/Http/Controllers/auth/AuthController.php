<?php

namespace App\Http\Controllers\auth;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;

class authController extends Controller
{
    public function getLogin(Request $request)
    {

        $username = $request->username;
        $password = $request->password;
        if (Auth::attempt(['username' => $username, 'password' => $password])) {
            return redirect()->intended('/home');
        }
        return ('/login');


    }

    public function postLogin()
    {


    }

    public function getLogout()
    {


    }
}
