@extends('layout.reset')
@section('title','New Password')
@section('content')

    @include('layout.error')


    <form class="form-horizontal" method="post" action="{{url('/password_reset/'.$token)}}">
        {{csrf_field()}}
        <div class="col-md-9">
        <div class="form-group">
            <label for="inputpassword" class="col-sm-2 control-label">New Password</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="newpassword" placeholder="Input New Password"
                       name="new_password">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">Confirm New Password</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="inputPassword3" placeholder="Confirm New Password"
                       name="confirm_password">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Save</button>
            </div>
        </div>
</div>
    </form>
@endsection