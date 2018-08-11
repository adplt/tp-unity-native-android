<?php

namespace App\Http\Controllers;

use App\ResetPasswordModel;
use App\StaffModel;
use App\BranchModel;
use Mail;
use Illuminate\Http\Request;

use App\Http\Requests;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Auth;

class StaffController extends Controller
{

    public function doLogin(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'username' => 'required',
            'password' => 'required'
        ]);
        if ($validator->fails()) {
            return redirect('/')->withErrors($validator)->withInput();
        }
        $username = $request->username;
        $password = $request->password;

        if (Auth::attempt(['username' => $username, 'password' => $password])) {
            //dd (json_encode($request->user()));
            if($request->user()->status == 1) {
                return redirect('/staffRegis');
            }else{
                return redirect('/home');
            }
            //
        }
        /*else if (StaffModel::where('username',$username)->where('password',Hash::make($password))->count()==1)
        {
            return redirect()->intended('/home');
        }*/
        return redirect('/')->with('message', 'Login Failed');
    }


    /*   public function get_login()
       {
           return View::make('app.form');
       }

       public function post_login()
       {
           // tentukan validasi
           $input = Input::get();
           $validasi = array
           (
               'username' => 'required|min:3',
               'password' => 'required|min:6'
           );
           $valid = Validator::make($input, $validasi);

           // jika valid
           if($valid->passes())
           {
               // proses inputan
               $email = Input::get('username');
               $password = Input::get('password');
               $password = Input::get('password');
               $inputan = array('username' => $email, 'password' => $password);

               // jika email & password ada pada database (authentikasi diterima)
               if(Auth::attempt($inputan))
               {
                   return Redirect::to_route('home')
                       ->with('class', 'alert-success')
                       ->with('judul', 'Login Success!')
                       ->with('pesan', 'Login Successfull!');
               }
               // jikak email & password tidak ada pada database
               else
               {
                   return Redirect::back()
                       ->with('class', 'alert-error')
                       ->with('judul', 'Invalid')
                       ->with('pesan', 'Username or Password Invalid!');
               }
           }
           //tidak valid
           {
               return Redirect::back()
                   ->with_input()
                   ->with_errors($valid);
           }
       }
   */
    public function newStaff(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'name' => 'required | min:3 |max:50',
            'email' => 'required | min:3 |max:50',
            'username' => 'required|min:3|max:20',
            'password' => 'required',
            'confirm_password' => 'required'
        ]);
        if ($validator->fails()) {
            return 'asd';redirect('/staffRegis')->withErrors($validator)->withInput();
        }

        $staff = new StaffModel();
        $staff->staff_name = $request->name;
        $staff->id_branch=$request->branch;
        $staff->email = $request->email;
        $staff->username = $request->username;
        $staff->password = Hash::make($request->password);
        $staff->save();

        return redirect('/')->with('message', 'Registration Success');


        // $degree = new DegreeModel();
        //$degree->degree_name = $request->degree;
        //$degree->save();
        //dd(json_encode($degree));
        //$team_promotion->id = $degree->id;

        // $team_promotion->birth_date = date('Y-m-d', strtotime($request->birth));
        //dd(json_encode($team_promotion));
    }

    public function regisstaff()
    {

        $branchs = BranchModel::all();
        $staffs =  StaffModel::join('branch', 'staff.id_branch', '=', 'branch.id')->get();

        //$tps = TeamPromotionModel::all();
        return view('staffRegis', ['branchs'=>$branchs,'staffs' => $staffs]);

    }




    public function changePassword()
    {
        return view('changePassword');
    }

    public function changePassword2()
    {
        return view('changePassword2');
    }


    public function savePassword(Request $request)
    {
        $user = Auth::user();
        $validator = Validator::make($request->all(), [
            'current_password' => 'required|pass_check',
            'new_password' => 'required|pass_alpha_num|min:6',
            'confirm_password' => 'required|same:new_password'
        ]);
        if ($validator->fails()) {

            return redirect('/changePassword')->withErrors($validator)->withInput();
        }

        $user->password = Hash::make($request->new_password);
        $user->save();
        return redirect('/home');
    }


    public function savePassword2(Request $request)
    {
        $user = Auth::user();
        $validator = Validator::make($request->all(), [
            'current_password' => 'required|pass_check',
            'new_password' => 'required|pass_alpha_num|min:6',
            'confirm_password' => 'required|same:new_password'
        ]);
        if ($validator->fails()) {

            return redirect('/changePassword2')->withErrors($validator)->withInput();
        }

        $user->password = Hash::make($request->new_password);
        $user->save();
        return redirect('/');
    }



    public function forgotPassword(Request $request)
    {

        $validator = Validator::make($request->all(), [
            'email' => 'required'

        ]);
        if ($validator->fails()) {

            return redirect('/password_reset')->withErrors($validator)->withInput();
        }

        $staff = StaffModel::where('email', $request->email)->first();

        if ($staff != null) {
            $ResetPassword = new ResetPasswordModel();
            $ResetPassword->email = $request->email;
            $ResetPassword->token = md5(time());
            $ResetPassword->save();
          Mail::send('emails.forgot', ['token' => $ResetPassword->token, 'staff' => $staff], function ($m) use ($request, $staff) {
                $m->from('herri.tpunity2016@gmail.com', 'Admin TPUnity');
                $m->to($request->email, $staff->staff_name)->subject('Forgot Password');
            });



            return redirect('/');
        }

        return redirect('/password_reset')->withErrors(['Email Not Registered']);

    }

    public function resetpassword($token)
    {
        $reset = ResetPasswordModel::where('token', $token)->first();
        if ($reset != null) {
            $staff = StaffModel::where('email', $reset->email)->first();
            if ($staff == null) return redirect('/');
            return view('newPassword', ['token' => $token]);
        } else return redirect('/')->withErrors('Token error');
    }

    public function newPassword($token, Request $request)
    {
        $reset = ResetPasswordModel::where('token', $token)->first();
        if ($reset != null) {
            $staff = StaffModel::where('email', $reset->email)->first();
            if ($staff == null) return redirect('/');
            $validator = Validator::make($request->all(), [
                'new_password' => 'required|pass_alpha_num|min:6',
                'confirm_password' => 'required|same:new_password'
            ]);
            if ($validator->fails()) {
                return redirect('/newPassword/' . $token)->withErrors($validator)->withInput();
                //password_reset
            }

            $staff->password = Hash::make($request->new_password);
            $staff->save();
            ResetPasswordModel::where('token', $token)->delete();
            return redirect('/');
        } else return redirect('/')->withErrors('Token error');

    }

}
