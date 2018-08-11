<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/home', 'HomeController@dashboard');
//Route::get('/home', 'StudentCandidateController@chart');

Route::get('/input_prm', function () {
    return view('input_prm');
});


Route::get('/staffRegis', function () {
    return view('StaffRegis');
});
Route::get('/password_reset', function () {
    return view('password_reset');
});
Route::post('/password_reset/{token}', 'StaffController@newPassword');
Route::get('/password_reset/{token}', 'StaffController@resetpassword');
Route::get('/changePassword', 'StaffController@changePassword');
Route::get('/changePassword2', 'StaffController@changePassword2');
Route::post('/changePassword', 'StaffController@savePassword');
Route::post('/changePassword2', 'StaffController@savePassword2');

Route::post('/password_reset', 'StaffController@forgotPassword');

/*Route::get('/forgotPassword', function(){
    Mail::send('emails.forgot', ['user'=>'Atria'], function ($m) {
        $m->from('herri.tpunity2016@gmail.com', 'Admin TPUnity');

        $m->to('tya.atria@yahoo.com', 'atria')->subject('Forgot');
    });
});*/

Route::get('/password_reset', function () {
    return view('password_reset');
});

Route::get('/report', function () {
    return view('report');
});


Route::get('/product', function () {
    return view('product');
});
Route::get('/', function () {
    return view('login');
});
/*Route::get('/login', function () {
    return view('login');
});*/

/*//Halaman beranda yg di akses setelah login (localhost:8000/beranda)
Route::get('/home', array('before' => 'auth', function () {
   return view ('home');
}));
//Halaman logout (localhost:8000/user/logout)
Route::get('/logout', array('before' => 'auth', function () {
   return view('logout');
}));*/
//profile


Route::get('/profileDetail', function () {
    return view('profile.detail');
});
Route::get('/profile', function () {
    return view('profile.index');
});
//Route::post('/filterProfileList', 'TeamPromotionController@filterProfileList');
//Route::get('/profile', 'TeamPromotionController@degree');
Route::get('/profile', 'TeamPromotionController@profile');
Route::get('/profile/delete/{id}', 'TeamPromotionController@destroy');
Route::get('/profile/{id}', 'TeamPromotionController@show');
Route::get('/tprequest', 'TeamPromotionController@tprequest');
Route::get('/tprequest/{id}/accept', 'TeamPromotionController@accept');
Route::get('/tprequest/{id}/decline', 'TeamPromotionController@decline');


Route::get('/absence', 'AbsenceController@absence');
Route::get('/schedule', 'ScheduleController@student');
Route::post('/updateSchedule', 'ScheduleController@updateSchedule');
//Route::get('/schedule/update/{id}', 'ScheduleController@updateSchedule');
Route::get('/updateSchedule/{id}', 'ScheduleController@update');


//FOLLOW UP CONTROLLER
Route::get('/followUp', function () {
    return view('followUp');
});
Route::get('/followUp', 'StudentCandidateController@student');
Route::post('/filterFollowUp', 'StudentCandidateController@filterFollowUp');
//Route::get('/followUp', 'DataController@data');

//EVENT CONTROLLER
Route::get('/event', 'EventController@index');
Route::post('/filterEvent', 'EventController@filterEvent');
Route::get('/addEvent', 'EventController@add');
Route::post('/addEvent', 'EventController@addEvent');
//Route::get('/updateEvent', 'EventController@update');
Route::get('/event/update/{id}', 'EventController@update');
Route::post('/updateEvent', 'EventController@updateEvent');
Route::get('/event/{id}', 'EventController@show');
Route::get('/event/delete/{id}', 'EventController@destroy');

//event
//Route::get('/event', function () {
//    return view('event.index');
//});
Route::get('/eventDetail', function () {
    return view('event.detail');
});
Route::get('/eventUpdate', function () {
    return view('event.update');
});


//product
Route::post('/filterProduct', 'ProductController@filterProduct');
Route::get('/addProduct', 'ProductController@add');
Route::post('/addProduct', 'ProductController@addProduct');
Route::post('/updateProduct', 'ProductController@updateProduct');
Route::get('/product', 'ProductController@index');
Route::get('/product/{id}', 'ProductController@show');
Route::get('/product/update/{id}', 'ProductController@update');
Route::get('/product/delete/{id}', 'ProductController@destroy');


Route::get('auth/register', 'Auth\AuthController@getRegister');
Route::post('auth/register', 'Auth\AuthController@postRegister');

Route::get('/staffRegis', 'StaffController@regisstaff');
Route::post('/newStaff', 'StaffController@newStaff');
Route::post('/doLogin', 'StaffController@doLogin');

Route::post('/', 'TeamPromotionController@registrationtp');
Route::get('/', 'TeamPromotionController@registp');
Route::get('/cobagcm', function(){
    $data = array('message' => 'Hello World!');

// The recipient registration tokens for this notification
// https://developer.android.com/google/gcm/
    $ids = array('AIzaSyCxHT4SVDOjOKMAuYEuga_slSOz_VDyc8s');

    // Insert real GCM API key from the Google APIs Console
    // https://code.google.com/apis/console/
    $apiKey = 'AIzaSyBxCcf5PPfk5ajZI2AzmSzTdJTpjabtuxE';

    // Set POST request body
    $post = array(
        'registration_ids'  => $ids,
        'notification'=>[
            'title' => 'coba',
            'icon' => 'myicon',
        ]
    );

    // Set CURL request headers
    $headers = array(
        'Authorization: key=' . $apiKey,
        'Content-Type: application/json'
    );

    // Initialize curl handle
    $ch = curl_init();

    // Set URL to GCM push endpoint
    curl_setopt($ch, CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send');

    // Set request method to POST
    curl_setopt($ch, CURLOPT_POST, true);

    // Set custom request headers
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

    // Get the response back as string instead of printing it
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Set JSON post data
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($post));

    curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);

    // Actually send the request
    $result = curl_exec($ch);

    // Handle errors
    if (curl_errno($ch))
    {
        return 'GCM error: ' . curl_error($ch);
    }

    // Close curl handle
    curl_close($ch);

    // Debug GCM response
    return $result;
});