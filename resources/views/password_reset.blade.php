@extends('layout.reset')
@section('title','Reset Password')
@section('content')

    @include('layout.error')


    <h4>Input your email to reset password</h4>

    <form method="post" action="{{url('/password_reset')}}">
        {{csrf_field()}}
        <label class="form-inline">
            <div class="form-group">
                <label for="exampleInputEmail1"></label>
                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email" name="email">
                <button type="submit" class="btn btn-default">Send</button>
                <a class="btn btn-default" href="{{url('/')}}" role="button">Cancel</a>
            </div>
        </label>

    </form>




    {{-- <form class="form-inline" method="post" action="{{url('/password_reset')}}">

         <div class="form-group">
             <label class="sr-only" for="exampleInputEmail3">Email Address</label>
             <input type="email" class="form-control" id="exampleInputEmail3" placeholder="Email" name="staffemail">
         </div>

         <button type="submit" class="btn btn-default">Send</button>
     </form>--}}

@endsection