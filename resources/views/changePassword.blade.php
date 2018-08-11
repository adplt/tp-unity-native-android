@extends('layout.main')
@section('title','Change Password')
@section('content')

    <h2>Change Password</h2>
    <div class="jumbotron">
        @include('layout.error')
        <form class="form-horizontal" method="post" action="{{url('/changePassword')}}">
            {{csrf_field()}}
            <div class="form-group">
                <label for="inputCurrentPass" class="col-sm-2 control-label">Current Password</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="CurrentPassword" placeholder="Input Current Password"
                           name="current_password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword1" class="col-sm-2 control-label">New Password</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="inputPassword1" placeholder="Input New Password"
                           name="new_password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword2" class="col-sm-2 control-label">Confirm New Password</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="inputPassword2" placeholder="Confirm New Password"
                           name="confirm_password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">

                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Save</button>

                    <a class="btn btn-default" href="{{url('/home')}}" role="button">Cancel</a>
                </div>
            </div>
    </div>
    </form>
@endsection