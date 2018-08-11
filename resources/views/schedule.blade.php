@extends('layout.main')
@section('title','Schedule')
@section('content')

    <h2>Team Promotion Schedule</h2>
    @if(isset($_GET['msg']) && $_GET['msg'].isNonEmptyString())
        <div class="alert alert-success">
            <b>{{ $_GET['msg'] }}</b>
        </div>
    @endif
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Saerch Data By:</h3>
                </div>
                <div class="panel-body">
                    <form method="get" action="{{url('/schedule')}}">
                        <div class="form-group">
                            <label for="inputName">Date</label>
                            <input type="text" id="inputdate" class="form-control datepicker" name="date_search" value="{{ $date_search }}"/>
                        </div>


                        <button type="submit" class="btn btn-default">Search</button>
                        <a href="{{url('/schedule')}}" class="btn btn-default">All</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">Schedule List</div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>No PRM</th>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php $i = 1; ?>
                    @foreach($supports as $support)
                        <tr>
                            <th scope="row">{{$i++}}</th>
                            <td>{{$support->no_prm}}</td>
                            <td>{{$support->tp_name}}</td>
                            <td>{{date('Y-m-d', strtotime($support->support_from))}}</td>
                            <td>{{date('H:i', strtotime($support->support_from)).' - '.date('H:i', strtotime($support->support_until))}}</td>
                            <td>{{$support->description}}</td>
                            <td>
                                <div class="btn-group btn-group-xs " role="group">

                                    <a href="{{url('/updateSchedule/'.$support->id_support)}}" class="btn btn-default">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"> Edit</span>
                                    </a>

                                </div>
                            </td>

                        </tr>
                    @endforeach
                    </tbody>
                </table>
            </div>
        </div>
        <div align="center">
            {!! $supports->appends(['date_search' => $date_search])->render() !!}
        </div>
    </div>
    <script>
        $(function () {
            $("#inputdate").datepicker();
            $("#inputdate").datepicker('option', 'dateFormat', 'yy-mm-dd');
            $("#inputdate").datepicker("setDate", "{{$date_search}}");

        });
    </script>
@endsection