@extends('layout.main')
@section('title','Product Detail')
@section('content')
    <h2>Product Detail</h2>


    <div class="row">

        <div class="col-md-12">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Product Detail</h3>
                </div>
                <div class="panel-body">

                    <ul class="list-group">
                        <li class="list-group-item">Faculty:{{$degree->faculty_name}}</li>
                        <li class="list-group-item">Degree:{{$degree->degree_name}}</li>

                        <li class="list-group-item">Description:{{$degree->note}}</li>
                    </ul>
                    <div class="form-group">
                        <a class="btn btn-default" href="{{url('/product')}}" role="button">Back</a>
                    </div>
                </div>
            </div>
        </div>
    </div>


@endsection