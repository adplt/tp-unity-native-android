<?php

namespace App\Http\Controllers;

use App\BranchModel;
use App\DegreeModel;

use App\TeamPromotionModel;

use Illuminate\Http\Request;
use Illuminate\Database\Eloquent;

use App\Http\Requests;
use Illuminate\Support\Facades\Auth;


class TeamPromotionController extends Controller
{


    /* public function degree()
     {

         $degree = DegreeModel::all();
         $team_promotions = DegreeModel::join('team_promotion','team_promotion.id_degree' , '=', 'degree.id')->get();
         return view('Sprofile', ['degree'=>$degree,'team_promotions'=>$team_promotions]);

     }*/

    private function getAuthUser() {
        return Auth::user();
    }

    public function registrationtp(request $request)
    {


        /* $validator = Validator::make($request->all(), [
             'fullName' => 'required | min:3 |max:50',
             'degree' => 'required',
             'birth' => 'required ',
             'phoneNumber' => 'required |numeric',
             'email' => 'required |email',

         ]);
         if ($validator->fails()) {

             return redirect('/login')->withErrors($validator)->withInput();
         }*/
        $team_promotion = new TeamPromotionModel();
        $team_promotion->tp_name = $request->fullName;
        $team_promotion->branch_id = $request->branch;
        $team_promotion->id_degree = $request->degree;

        $team_promotion->birth_date = date('Y-m-d', strtotime($request->birth));
        $team_promotion->phone_number = $request->phoneNumber;
        $team_promotion->email = $request->email;
//        dd($team_promotion);
//        $team_promotion->
//        dd(json_encode($team_promotion));

        $team_promotion->save();
        // return redirect('/login');
    }

    public function registp()
    {
        $degrees = DegreeModel::all();
        $team_promotions = TeamPromotionModel::join('degree', 'team_promotion.id_degree', '=', 'degree.id')->get();

        $branchs = BranchModel::all();
        //$tps = TeamPromotionModel::all();
        return view('login', ['degrees' => $degrees, 'team_promotions' => $team_promotions, 'branchs' => $branchs]);

    }

    /* public function seacrhing()
     {

         $searchterm = Input::get('searchinput');

         if ($searchterm){

             $products = DB::table('products');
             $results = $products->where('name', 'LIKE', '%'. $searchterm .'%')
                 ->orWhere('description', 'LIKE', '%'. $searchterm .'%')
                 ->orWhere('brand', 'LIKE', '%'. $searchterm .'%')
                 ->get();

             return view('search')->with('products', $results);

         }
     }*/

    public function profile(Request $request)
    {

        //list diurutkan dari score tertinggi sampai terendah
        /*$team_promotions = TeamPromotionModel:: where('accepted_tp', 1)->orderBy('score', 'desc')->
        join('degree', 'team_promotion.id_degree', '=', 'degree.id')->get();*/
        $tpname = $request->tpname;

        $user = $this->getAuthUser();

        $team_promotions = DegreeModel::
            join('team_promotion', 'degree.id', '=', 'team_promotion.id_degree')
//            ->join('staff', 'team_promotion.id_staff', '=', 'staff.id')
            ->where('accepted_tp', 1)
            ->where('team_promotion.branch_id', $user->id_branch)
            ->orderBy('score', 'desc');

//        dd($team_promotions->get());
        if($tpname != null && $tpname.isNonEmptyString()) $team_promotions = $team_promotions->where('tp_name', 'LIKE', '%'.$tpname.'%');

        $team_promotions = $team_promotions->paginate(env('PAGINATION_COUNT'));

        return view('profile.index', ['team_promotions' => $team_promotions, 'tpname' => $tpname]);
    }


    public function show($id)
    {
        //$degrees = DegreeModel::all();
        $team_promotion = TeamPromotionModel::join('degree', 'degree.id', '=', 'team_promotion.id_degree')
            ->where('team_promotion.id', $id)->first();

        //$team_promotion = TeamPromotionModel::find($id);
        return view('profile.detail', ['team_promotion' => $team_promotion]);
    }

    public function destroy($id)
    {

        TeamPromotionModel::find($id)->delete();
        return redirect('/profile');
    }


    /*  public function filterProfileList(Request $request)
      {
          $tpname = $request->tpname;

          $team_promotion = TeamPromotionModel::all();
          $team_promotion = [];


          foreach ($team_promotion as $team_promotion) {
              $filter = true;
              if (trim($tpname) != '') {
                  if ($team_promotion == null || stripos($team_promotion->tp_name, $tpname) === false) {
                      $filter = false;
                  }
              }
              $tps = TeamPromotionModel::all();
              return view('/profile.index', ['team_promotion' => $team_promotion]);
          }
      }*/

    public function tprequest(Request $request)
    {
        $user = $this->getAuthUser();

        $degrees = DegreeModel::all();
        $team_promotions = DegreeModel::
        join('team_promotion', 'degree.id', '=', 'team_promotion.id_degree')
//            ->join('staff', 'team_promotion.id_staff', '=' , 'staff.id')
            ->where('accepted_tp', 0)
            ->where('team_promotion.branch_id', $user->id_branch)
            ->get();

//dd($team_promotions);

        // $team_promotions = TeamPromotionModel::
        //where('accepted_tp', 0)->get();
        return view('tprequest', ['degrees' => $degrees, 'team_promotions' => $team_promotions]);
    }

    private function generateId() {
        $tp_code = "PRM";
        $tp_last_data = TeamPromotionModel::orderBy('no_prm','desc')->first();
        if($tp_last_data != null) {
            $tp_last_id = substr($tp_last_data->no_prm, 3) + 1;
        } else {
            $tp_last_id = "14000";
        }

        $curr_id = $tp_code.$tp_last_id;

        return $curr_id;
    }

    public function accept($id)
    {
        $user = $this->getAuthUser();

        $tp = TeamPromotionModel::findOrFail($id);
        $tp->no_prm = $this->generateId();
        $tp->accepted_tp = 1;
        $tp->join_date = date('0-0-0');
        $tp->id_staff = $user->id;
        $tp->save();
        return redirect('/profile');
    }

    public function decline($id)
    {
        TeamPromotionModel::find($id)->delete();
        return redirect('/tprequest');

    }

}