@extends('layout.login')
@section('title','Login')
@section('content')

    <center><h2>Login</h2></center>
    <div class="col-md-12" style="margin-top:25px">

        <form class="form-horizontal" action="/doLogin" method="post">
            {{csrf_field()}}
            <div class="form-group">
                <label for="prm" class="col-sm-4 control-label">Username</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="username" placeholder="Username" name="username">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-4 control-label">Password</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password"
                           name="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Remember me
                        </label>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <input type="submit" class="btn btn-default" value="Login">

                </div>
            </div>
        </form>
    </div>


@endsection