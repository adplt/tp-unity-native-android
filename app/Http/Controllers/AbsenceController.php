<?php

namespace App\Http\Controllers;

use App\AbsenceModel;
use App\TeamPromotionModel;
use Illuminate\Http\Request;
use Illuminate\Support\Collection;

use App\Http\Requests;

class AbsenceController extends Controller
{

    public function absence(Request $request)
    {
        $date_search = $request->date_search;
        $team_promotion = TeamPromotionModel::all();

        $absences = TeamPromotionModel::where('accepted_tp', 1)->join('absence', 'team_promotion.no_prm', '=', 'absence.no_prm');
        if($date_search!=null)$absences->whereDate('clock_in','=',$date_search);

        $absences = $absences->paginate(env('PAGINATION_COUNT'));

        return view('absence', ['absences' => $absences,'team_promotion' => $team_promotion, 'date_search' => $date_search]);




        /*$degree = DegreeModel::all();
        $team_promotions = DegreeModel:: where('accepted_tp', 1)->orderBy('score', 'desc')->
        join('team_promotion', 'degree.id', '=', 'team_promotion.id_degree')->get();

        return view('profile.index', ['team_promotions' => $team_promotions, 'degree' => $degree]);*/





    }
}
