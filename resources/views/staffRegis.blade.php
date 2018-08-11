@extends('layout.main2')
@section('title','Staff Registration')
@section('content')

    <h2>Staff Registration</h2>
    <div class="jumbotron">
        @include('layout.error')
        <form class="form-horizontal" method="post" action="{{url('/newStaff')}}">
            {{csrf_field()}}

            <div class="form-group">
                <label for="inputName" class="col-sm-2 control-label">Name</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="name" placeholder="Input Name" name="name">
                </div>
            </div>

            <div class="form-group">
                <label for="brc" class="col-sm-2 control-label">Branch</label>
                <div class="col-sm-4">
                    <select class="form-control" id="brc" name="branch">
                        @foreach($branchs as $branch)
                            <option value="{{$branch->id}}">{{$branch->branch_name}}</option>
                        @endforeach
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-4">
                    <input type="email" class="form-control" id="inputEmail3" placeholder="Input Email" name="email">
                </div>
            </div>
            <div class="form-group">
                <label for="inputUsername" class="col-sm-2 control-label">Username</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="Username" placeholder="Input Username" name="username">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword1" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="inputPassword1" placeholder="Input Password"
                           name="password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword2" class="col-sm-2 control-label">Confirm Password</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="inputPassword2" placeholder="Confirm Password"
                           name="confirm_password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">

                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default btn-success" role="button">Register</button>
                    <a class="btn btn-default btn-default" href="{{url('/')}}" role="button">Cancel</a>
                </div>
            </div>
        </form>
    </div>
   {{-- <script>
        $(function () {
            $('.btn-success').click(function () {
                if (alert('Staff Registration Success')) {

                } else {
                    return false;
                }
            });
        });
    </script>--}}
@endsection