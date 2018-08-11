@extends('layout.main')
@section('title','Event Data')
@section('content')

    <html>
    <body>

    <h2>Event Data</h2>
    @if(isset($_GET['msg']) && $_GET['msg'].isNonEmptyString())
        <div class="alert alert-success">
            <b>{{ $_GET['msg'] }}</b>
        </div>
    @endif
    <div class="row">

        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Search</h3>
                </div>
                <div class="panel-body">
                    <form>{{--<form method="post" action="{{url('/filterProfileList')}}">--}}
                        <div class="panel-body">
                            <form method="get" action="{{url('/event')}}">
                                <div class="form-group">
                                    <label for="inputName">Date</label>
                                    <input type="text" id="inputdate" class="form-control datepicker"
                                           name="date_search" value="{{$date_search}}"/>
                                </div>
                                <button type="submit" class="btn btn-default">Search</button>
                                <a href="{{url('/event')}}" class="btn btn-default">All</a>
                            </form>
                        </div>
                        {{--<div class="form-group">
                            <label for="inputName">Event Name</label>
                            <input type="text" id="inputName" class="form-control" name="eventname"/>
                        </div>
                        <button type="submit" class="btn btn-default">Search</button>
--}}
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">Event List</div>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Location</th>
                        <th>PIC</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php $i = 1; ?>
                    @foreach($events as $event)
                        <tr>
                            <th scope="row">{{$i++}}</th>
                            <td>{{$event->event_name}}</td>
                            <td>{{$event->start}}</td>
                            <td>{{$event->end}}</td>
                            <td>{{$event->location}}</td>
                            <td>{{$event->staff_name}}</td>
                            <td>
                                <div class="btn-group btn-group-xs " role="group">
                                    <a href="{{url('/event/'.$event->id)}}"
                                       class="btn btn-default">
                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"> View</span>
                                    </a>
                                    <a href="{{url('/event/update/'.$event->id)}}" class="btn btn-default">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"> Edit</span>
                                    </a>
                                    <a href="{{url('/event/delete/'.$event->id)}}"
                                       class="btn btn-default btn btn-delete">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"> Delete</span>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    @endforeach
                    </tbody>
                </table>

            </div>

            <a class="btn btn-default" href="{{url('/addEvent')}}" role="button"><i
                        class="glyphicon glyphicon-plus"></i>Add</a>
            <div align="center">
                {!! $events->appends(['date_search' => $date_search])->render() !!}
            </div>
        </div>
    </div>

    {{-- <nav>
         <ul class="pagination small" style="align-content: center">
             <li>
                 <a href="#" aria-label="Previous">
                     <span aria-hidden="true">&laquo;</span>
                 </a>
             </li>
             <li><a href="#">1</a></li>
             <li><a href="#">2</a></li>
             <li><a href="#">3</a></li>
             <li>
                 <a href="#" aria-label="Next">
                     <span aria-hidden="true">&raquo;</span>
                 </a>
             </li>
         </ul>
     </nav>--}}
    </body>
    </html>
    <script>
        $(function () {
            $('.btn-delete').click(function () {
                if (confirm('Are you sure?')) {

                } else {
                    return false;
                }
            });
        });
    </script>

@endsection