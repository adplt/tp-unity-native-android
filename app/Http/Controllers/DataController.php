<?php

namespace App\Http\Controllers;
use App\DataModel;

use Illuminate\Http\Request;

use App\Http\Requests;

class DataController extends Controller
{
    public function data()
    {
        $data = DataModel::all();
        return view('followUp', ['data' => $data]);
    }
}
