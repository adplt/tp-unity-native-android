<?php

namespace App\Http\Controllers;

use App\TeamPromotionModel;
use App\Http\Requests;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;

class HomeController extends Controller
{
    public function dashboard()
    {
        $tps = TeamPromotionModel::where('accepted_tp', 1)->orderBy('score', 'desc')->take(10)->get();

        $chart = DB::table('student_candidate')
            ->select(DB::raw('DATE_FORMAT(follow_up_date,\'%b %y\') as month'), DB::raw('count(*) as total'))
            ->groupBy('month')
            ->get();
//        dd($chart);
        return view('home', ['tps' => $tps, 'chart' => $chart]);


    }


    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    /**public function index()
     * {
     * return view('home');
     * }**/
}
