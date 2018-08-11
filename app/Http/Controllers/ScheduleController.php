<?php

namespace App\Http\Controllers;

use App\ScheduleModel;
use App\SupportModel;
use Illuminate\Http\Request;

use App\Http\Requests;
//use Illuminate\Support\Facades\DB;

class ScheduleController extends Controller
{
    public function student(Request $request)
    {
        // $schedules = ScheduleModel::all();
        // return view('schedule', ['schedules' => $schedules]);
        $date_search = $request->date_search;

        $supports = SupportModel::where('accepted_tp', 1)->join('schedule', 'schedule.id_support', '=', 'support.id')
            ->join('team_promotion', 'schedule.no_prm', '=', 'team_promotion.no_prm');
        if ($date_search != null) $supports->whereDate('support_from', '=', $date_search);

        $supports = $supports->paginate(env('PAGINATION_COUNT'));
        return view('schedule', ['supports' => $supports, 'date_search' => $date_search]);
    }


    public function index()
    {
        return view('schedule');
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function update($id)
    {
        $schedules = ScheduleModel::where('id_support',$id)->get()->first();

        //dd(json_encode($event));
        //dd($schedules);
        return view('updateSchedule', ['schedules' => $schedules]);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    function updateSchedule(Request $request)
    {
        $support = SupportModel::find($request->id);
//dd(json_encode($support));
        $date = substr($support->support_from."",0,strpos($support->support_from," "));
        //dd(date("Y/m/d H:i:s", strtotime($date.$request->StartTime)));
        $support->support_from = date("Y/m/d H:i:s", strtotime($date . $request->StartTime));
        $support->support_until = date("Y/m/d H:i:s", strtotime($date . $request->EndTime));
        $support->description = $request->description;
        $support->save();

        return redirect()->action('ScheduleController@student', ['msg' => 'Update Success']);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
