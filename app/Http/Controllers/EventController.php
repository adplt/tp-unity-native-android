<?php

namespace App\Http\Controllers;

use App\BranchModel;
use App\EventModel;
use App\StaffModel;
use Illuminate\Http\Request;

use App\Http\Requests;
use Illuminate\Support\Facades\Validator;

use Illuminate\Support\Facades\Auth;

class EventController extends Controller
{
    private function getAuthUser() {
        return Auth::user();
    }

    public function addEvent(Request $request)
    {
        $user = $this->getAuthUser();

        $validator = Validator::make($request->all(), [
            'EventName' => 'required | min:3 |max:50',
            'Date' => 'required|date',
            'StartTime' => 'required',
            'EndTime' => 'required|after:StartTime',
            'Location' => 'required | min:3',
            'TotalTP' => 'required |numeric',
            'Picture' => 'required',
            'PICName' => 'required'
        ]);
        if ($validator->fails()) {

            return redirect('/addEvent')->withErrors($validator)->withInput();
        }
        $event = new EventModel();
        $event->event_name = $request->EventName;
        $event->start = date('y/m/d H:m:s', strtotime($request->Date . ' ' . $request->StartTime));
        $event->end = date('y/m/d H:m:s', strtotime($request->Date . ' ' . $request->EndTime));
        $event->location = $request->Location;
        $event->total_tp = $request->TotalTP;
        $img = $request->file('Picture');
        $event->image_path = md5('event' . time()) . '.' . $img->getClientOriginalExtension();
        $img->move('img/event/', $event->image_path);
        $event->note = $request->Notes;
        $event->id_staff = $user->id; //ini di join dulu biar yang muncul namanya bukan id
//        $event->branch_id = $user->id_branch;
        $event->save();
        return redirect()->action('EventController@index', ['msg' => 'Event has saved successfully']);


        /* if (trim($name) == ""  ) {
             return view('addExpo', ['error' => 'Name Must Be Filled']);
         }else if(strlen($name)<3){
             return view('addExpo',['error'=>'Name of length minumum 3']);
         }
         else if($datetime ==""){
             return view('addExpo', ['error' => 'Date and Time Must Be Filled']);
         }
         else if($location ==""){
             return view('addExpo', ['error' => 'Location Must Be Filled']);
         }
         else if($totaltp ==""){
             return view('addExpo', ['error' => 'Total TP Must Be Filled']);
         }
         else if($picture ==""){
             return view('addExpo', ['error' => 'Picture Must Be Uploaded']);
         }
         else if($picname ==""){
             return view('addExpo', ['error' => 'PIC Name Must Be Filled']);
         }*/

    }

    function add()
    {
        $staffs = StaffModel::all();
        return view('event.create', ['staffs' => $staffs]);
    }

    public function filterEvent(Request $request)
    {
        $eventname = $request->eventname;

        $all_events = EventModel::all();
        $event = [];


        foreach ($all_events as $event) {
            $filter = true;
            if (trim($eventname) != '') {
                if ($event->event == null || stripos($event->event_name, $eventname) == false) {
                    $filter = false;
                }
            }
        }


        return view('/event', ['event' => $event]);
    }


    /*  function index()
      {
          $events = EventModel::all();
          return view('event.index', ['events' => $events]);
      }*/
    function index(Request $request)
    {
        $user = $this->getAuthUser();

        $date_search = $request->date_search;

        $events = StaffModel::join('event', 'staff.id', '=', 'event.id_staff')->where('status', 0);

        if ($date_search != null) $events = $events->whereDate('start', '=', date('Y-m-d',strtotime($date_search)));

        $events = $events->where('staff.id_branch', $user->id_branch)->paginate(env('PAGINATION_COUNT'));

        return view('event.index', ['events' => $events, 'date_search' => $date_search]);
    }

    function show($id)
    {
        $event = StaffModel::join('event', 'event.id_staff', '=', 'staff.id')->where('event.id', $id)->first();
        if ($event == null) return redirect('/event')->withErrors('Event not found');
        return view('event.detail', ['event' => $event]);
    }

    function update($id)
    {
        //$event = EventModel::all();

        $event = EventModel::find($id);
        $staffs = StaffModel::all();
        //dd(json_encode($event));

        return view('event.update', ['event' => $event, 'staffs' => $staffs]);
    }

    function updateEvent(Request $request)
    {
        $event = EventModel::find($request->id);
        $event->event_name = $request->EventName;
        $event->start = date("Y/m/d H:i:s", strtotime($request->Date . $request->StartTime));
        $event->end = date("Y/m/d H:i:s", strtotime($request->Date . $request->EndTime));
        $event->location = $request->Location;
        $event->total_tp = $request->TotalTP;
        $img = $request->file('Picture');
        if ($img != null) {

            $event->image_path = md5('event' . time()) . '.' . $img->getClientOriginalExtension();
            $img->move('img/event/', $event->image_path);
        }


        $event->note = $request->Notes;
        $event->id_staff = $request->PICName;
        //dd(json_encode($event));
        $event->save();

        //dd(json_encode($event));
        return redirect()->action('EventController@index', ['msg' => 'Event has updated successfully']);
    }

    function destroy($id)
    {
        EventModel::find($id)->delete();
        return redirect('/event');
    }


}
