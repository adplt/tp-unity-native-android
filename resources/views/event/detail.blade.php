@extends('layout.main')
@section('title','Event Detail')
@section('content')
    <h2>Event Detail</h2>


    <div class="row">

        <div class="col-md-12">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Event Detail</h3>
                </div>
                <div class="panel-body">
                    <img src="{{url('/img/event/'.$event->image_path)}}" alt="pict" class="img-circle" width="260"
                         height="210">

                    <ul class="list-group">
                        <li class="list-group-item">Name:{{$event->event_name}}</li>
                        <li class="list-group-item">Date and Time:{{$event->start}} - {{$event->end}}</li>
                        <li class="list-group-item">Location: {{$event->location}}</li>
                        <li class="list-group-item">Total TP:{{$event->total_tp}}</li>
                        <li class="list-group-item">PIC Event:{{$event->staff_name}}</li>
                        <li class="list-group-item">Notes:{{$event->note}}</li>
                    </ul>
                    <div class="form-group">
                        <button id="publish" class="btn btn-default">Publish Now</button>
                        <a class="btn btn-default" href="{{url('/event')}}" role="button">Back</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://www.gstatic.com/firebasejs/live/3.0/firebase.js"></script>
    <script>
        $(function(){
            var config = {
                apiKey: "AIzaSyBxCcf5PPfk5ajZI2AzmSzTdJTpjabtuxE",
                authDomain: "tpunity.firebaseapp.com",
                databaseURL: "https://tpunity.firebaseio.com",
                storageBucket: "project-6484475432110519983.appspot.com",
            };
            firebase.initializeApp(config);
            firebase.auth().signInAnonymously();
            var db = firebase.database();
            $('#publish').click(function(){
                var key = db.ref().child('publish').push().key;
                db.ref('publish/'+key).set({title:'{{$event->event_name}}', time:'{{$event->start}}'});
            });
        });
    </script>


@endsection