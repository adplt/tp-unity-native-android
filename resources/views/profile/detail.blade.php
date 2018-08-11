@extends('layout.main')
@section('title','Profile List')
@section('content')
    <h2>Profile Detail List</h2>


    <div class="row">

        <div class="col-md-12">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Profile Detail</h3>
                </div>
                <div class="panel-body">

                    <img src="{{($team_promotion->picture)}}" alt="Picture" class="img-circle" width="260"
                         height="210">

                    <ul class="list-group">
                        <li class="list-group-item">Name:{{$team_promotion->tp_name}}</li>
                        <li class="list-group-item">PRM Number:{{$team_promotion->no_prm}}</li>
                        <li class="list-group-item">Degree: {{$team_promotion->degree_name}}</li>
                        <li class="list-group-item">Work Number:{{$team_promotion->work_number}}</li>
                        <li class="list-group-item">Phone Number:{{$team_promotion->phone_number}}</li>
                        <li class="list-group-item">Join Date:{{$team_promotion->join_date}}</li>
                        <li class="list-group-item">Scores:{{$team_promotion->score}}</li>
                    </ul>
                    <div class="form-group">

                        <a class="btn btn-default" href="{{url('/profile')}}" role="button">Allow Follow Up Acces</a>
                        <a class="btn btn-default" href="{{url('/profile')}}" role="button">Show All</a>
                    </div>
                </div>
            </div>
        </div>
    </div>


@endsection
