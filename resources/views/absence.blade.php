@extends('layout.main')
@section('title','Absence')
@section('content')
    <h2>Team Promotion Absence</h2>

    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Search Data By:</h3>
                </div>
                <div class="panel-body">
                    <form method="get" action="{{url('/absence')}}">
                        <div class="form-group">
                            <label for="inputName">Date</label>
                            <input type="text" id="inputdate" class="form-control datepicker" name="date_search" value="{{ $date_search }}"/>
                        </div>
                        <button type="submit" class="btn btn-default">Search</button>
                        <a href="{{url('/absence')}}" class="btn btn-default">All</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-9">

            <div class="row">
                <div class="col-md-11">
                    <div class="panel panel-default">
                        <div class="panel-heading">Absence List</div>

                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>No</th>
                                <th>PRM Number</th>
                                <th>Name</th>
                                <th>Date</th>
                                <th>Clock In</th>
                                <th>Clock Out</th>
                                <th>Description</th>
                            </tr>
                            </thead>
                            <tbody>
                            <?php $i = 1; ?>
                            @foreach($absences as $absence)
                                <tr>
                                    <th scope="row">{{$i++}}</th>
                                    <td>{{$absence->no_prm}}</td>
                                    <td>{{$absence->tp_name}}</td>
                                    <td>{{date('Y-m-d', strtotime($absence->clock_in))}}</td>
                                    <td>{{ date('H:i:s',strtotime($absence->clock_in))}}</td>
                                    <td>{{ date('H:i:s',strtotime($absence->clock_out))}}</td>
                                    <td>{{$absence->decription}}</td>
                                </tr>
                            @endforeach
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div align="center">
            {!! $absences->appends(['date_search' => $date_search])->render() !!}
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