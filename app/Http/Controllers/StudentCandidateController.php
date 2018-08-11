<?php

namespace App\Http\Controllers;

use App\DataModel;
use App\StudentCandidateModel;
use App\TeamPromotionModel;
use Carbon\Carbon;
use Illuminate\Http\Request;

use App\Http\Requests;
use Illuminate\Pagination\Paginator;
use Illuminate\Support\Facades\DB;
class StudentCandidateController extends Controller
{

    /* public function chart()
     {
         $chart = DB::table('student_candidate')
             ->select(DB::raw('DATE_FORMAT(follow_up_date,\'%b %y\') as month'), DB::raw('count(*) as total'))
             ->groupBy('month')
             ->get();
         return view('home', ['chart' => $chart]);

     }*/

    public function student(Request $request)
    {
        $tpname = $request->tpname;
        $followed = $request->followed;
        $notfollowed = $request->notfollowed;
        $interested = $request->interested;
        $datatype = $request->datatype;

        $all_student_candidates = null;

        if(trim($tpname) != '') {
            $all_student_candidates = StudentCandidateModel::
                                        join('team_promotion', 'student_candidate.no_prm', '=', 'team_promotion.no_prm')->
                                        orWhereNotNull('team_promotion.no_prm')->
                                        where('tp_name', 'LIKE', '%'.trim($tpname).'%');
//            dd($all_student_candidates->get());
        } else {
            $all_student_candidates = StudentCandidateModel::select("*");
        }

        if($followed != $notfollowed) {
            if($followed == 1) $all_student_candidates = $all_student_candidates->where('student_candidate.no_prm', '<>' , '0');
            else if($notfollowed == 1) $all_student_candidates = $all_student_candidates->where('student_candidate.no_prm', '0');
        }

        if ($interested != null) {
            $all_student_candidates = $all_student_candidates->where('student_candidate.result', '1');
        }

        if($datatype != null && $datatype != 0) {
            $all_student_candidates = $all_student_candidates->where('id_data', $datatype);
        }
//        $all_student_candidates = StudentCandidateModel::all();
//        $student_candidates = [];
//
//
//        foreach ($all_student_candidates as $student_candidate) {
//            $filter = true;
//            if (trim($tpname) != '') {
//                if ($student_candidate->team_promotion == null || stripos($student_candidate->team_promotion->tp_name, $tpname) === false) {
//                    $filter = false;
//                }
//            }
//            if ($followed != $notfollowed) {
//                if ($followed != null && $student_candidate->no_prm == null) {
//                    $filter = false;
//                }
//                if ($notfollowed != null && $student_candidate->no_prm != null) {
//                    $filter = false;
//                }
//            }
//
//            if ($datatype != 0 && $student_candidate->id_data != $datatype) {
//                $filter = false;
//            }
//
//            if ($interested != null && $student_candidate->result != $interested) {
//                $filter = false;
//            }
//
//            if ($filter == true) {
//                array_push($student_candidates, $student_candidate);
//            }
//
//        }

        $datas = DataModel::all();
//        dd($followed === 1);
//        return view('/followUp', $paginator->make($student_candidates, count($student_candidates), Input::get('limit') ?: '10'));

        return view('/followUp', ['student_candidates' => $all_student_candidates->paginate(env('PAGINATION_COUNT')), 'datas' => $datas, 'tpname' => $tpname, 'followed' => $followed, 'notfollowed' => $notfollowed, 'interested' => $interested, 'datatype' => $datatype]);
    }

}
